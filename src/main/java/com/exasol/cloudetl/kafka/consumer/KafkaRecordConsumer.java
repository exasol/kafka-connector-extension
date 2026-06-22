package com.exasol.cloudetl.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.exasol.ExaDataTypeException;
import com.exasol.ExaIterator;
import com.exasol.ExaIterationException;
import com.exasol.cloudetl.kafka.KafkaConnectorConstants;
import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerFactory;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;
import com.exasol.cloudetl.kafka.deserialization.DeserializationFactory;
import com.exasol.cloudetl.kafka.deserialization.FieldConverter;
import com.exasol.cloudetl.kafka.deserialization.FieldParser;
import com.exasol.cloudetl.kafka.deserialization.FieldSpecification;
import com.exasol.cloudetl.kafka.deserialization.GlobalFieldSpecification;
import com.exasol.cloudetl.kafka.deserialization.RowBuilder;
import com.exasol.errorreporting.ExaError;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.AuthenticationException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.InvalidTopicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaRecordConsumer implements RecordConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaRecordConsumer.class);

    private final KafkaConsumerProperties properties;
    private final int partitionId;
    private final long partitionStartOffset;
    private final List<Class<?>> outputColumnTypes;
    private final int tableColumnCount;
    private final long nodeId;
    private final String vmId;
    private final String topic;
    private final KafkaConsumer<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> consumer;
    private final long partitionEndOffset;
    private final int maxRecordsPerRun;
    private final int minRecordsPerRun;
    private final Duration timeout;
    private final List<GlobalFieldSpecification> recordFieldSpecifications;

    public KafkaRecordConsumer(final KafkaConsumerProperties properties, final int partitionId,
            final long partitionStartOffset, final List<Class<?>> outputColumnTypes,
            final int tableColumnCount, final long nodeId, final String vmId) {
        this.properties = properties;
        this.partitionId = partitionId;
        this.partitionStartOffset = partitionStartOffset;
        this.outputColumnTypes = outputColumnTypes;
        this.tableColumnCount = tableColumnCount;
        this.nodeId = nodeId;
        this.vmId = vmId;
        this.topic = properties.getTopic();
        this.consumer = getRecordConsumer();
        this.partitionEndOffset = getPartitionEndOffset();
        this.maxRecordsPerRun = properties.getMaxRecordsPerRun();
        this.minRecordsPerRun = properties.getMinRecordsPerRun();
        this.timeout = Duration.ofMillis(properties.getPollTimeoutMs());
        this.recordFieldSpecifications = FieldParser.get(properties.getRecordFields());
    }

    @Override
    public final void emit(final ExaIterator iterator) {
        long recordOffset = this.partitionStartOffset;
        int recordCount;
        long totalRecordCount = 0L;
        try {
            do {
                final ConsumerRecords<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> records =
                        this.consumer.poll(this.timeout);
                recordCount = records.count();
                totalRecordCount += recordCount;
                recordOffset = updateRecordOffset(emitRecords(iterator, records));
                LOGGER.info("Polled '{}' records, total '{}' records for partition '{}' in node '{}' and vm '{}'.",
                        recordCount, totalRecordCount, this.partitionId, this.nodeId, this.vmId);
            } while (shouldContinue(recordOffset, recordCount, totalRecordCount));
        } catch (final Exception exception) {
            handleExceptions(exception);
        } finally {
            this.consumer.close();
        }
    }

    private long updateRecordOffset(final long currentOffset) {
        return currentOffset == -1L ? getPartitionCurrentOffset() : currentOffset;
    }

    protected KafkaConsumer<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> getRecordConsumer() {
        final TopicPartition topicPartition = new TopicPartition(this.topic, this.partitionId);
        final List<GlobalFieldSpecification> recordFields = FieldParser.get(this.properties.getRecordFields());
        final DeserializationFactory.RecordDeserializers recordDeserializers =
                DeserializationFactory.getSerializers(recordFields, this.properties);
        final KafkaConsumer<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> newConsumer =
                KafkaConsumerFactory.apply(this.properties, recordDeserializers.getKeyDeserializer(),
                        recordDeserializers.getValueDeserializer());
        newConsumer.assign(Arrays.asList(topicPartition));
        newConsumer.seek(topicPartition, this.partitionStartOffset);
        return newConsumer;
    }

    private long emitRecords(final ExaIterator iterator,
            final ConsumerRecords<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> records)
            throws ExaIterationException, ExaDataTypeException {
        long lastRecordOffset = -1L;
        final FieldConverter fieldConverter = new FieldConverter(this.outputColumnTypes);
        for (final ConsumerRecord<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> consumerRecord : records) {
            lastRecordOffset = consumerRecord.offset();
            final List<Object> metadata = List.of(consumerRecord.partition(), consumerRecord.offset());
            final int columnsCount = this.tableColumnCount - metadata.size();
            final List<Object> row = new ArrayList<>(RowBuilder.buildRow(this.recordFieldSpecifications, consumerRecord, columnsCount));
            row.addAll(metadata);
            final List<Object> convertedRow = fieldConverter.convertRow(row);
            iterator.emit(convertedRow.toArray(new Object[0]));
        }
        return lastRecordOffset;
    }

    private boolean shouldContinue(final long recordOffset, final int recordCount, final long totalRecordCount) {
        return (this.properties.isConsumeAllOffsetsEnabled() && recordOffset < this.partitionEndOffset)
                || (recordCount >= this.minRecordsPerRun && totalRecordCount < this.maxRecordsPerRun);
    }

    private long getPartitionCurrentOffset() {
        final TopicPartition topicPartition = new TopicPartition(this.topic, this.partitionId);
        final long currentOffset = this.consumer.position(topicPartition) - 1;
        LOGGER.info("The current record offset for partition '{}' is '{}'.", this.partitionId, currentOffset);
        return currentOffset;
    }

    private long getPartitionEndOffset() {
        final TopicPartition topicPartition = new TopicPartition(this.topic, this.partitionId);
        final long endOffset = this.consumer.endOffsets(Arrays.asList(topicPartition)).get(topicPartition) - 1;
        LOGGER.info("The last record offset for partition '{}' is '{}'.", this.partitionId, endOffset);
        return endOffset;
    }

    private void handleExceptions(final Exception exception) {
        if (exception instanceof IllegalStateException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-20")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message("Consumer is not subscribed to the given topic or it is not assigned any partition of the topic.")
                    .mitigation("Please check that the Kafka topic is available and valid.")
                    .toString(), exception);
        } else if (exception instanceof InvalidTopicException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-21")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message("Provided topic is not valid.")
                    .mitigation("Please make sure that the Kafka topic is valid.")
                    .toString(), exception);
        } else if (exception instanceof AuthorizationException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-22")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message(KafkaConnectorConstants.AUTHORIZATION_ERROR_MESSAGE)
                    .parameter("CAUSE", exception.getMessage())
                    .mitigation(KafkaConnectorConstants.AUTHORIZATION_ERROR_MITIGATION)
                    .toString(), exception);
        } else if (exception instanceof AuthenticationException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-23")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message(KafkaConnectorConstants.AUTHENTICATION_ERROR_MESSAGE)
                    .parameter("CAUSE", exception.getMessage())
                    .mitigation(KafkaConnectorConstants.AUTHENTICATION_ERROR_MITIGATION)
                    .toString(), exception);
        }
        throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-4")
                .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                .message("It occurs for partition {{PARTITION_ID}} in node {{NODE_ID}} and vm {{VM_ID}}.")
                .parameter("PARTITION_ID", String.valueOf(this.partitionId))
                .parameter("NODE_ID", String.valueOf(this.nodeId))
                .parameter("VM_ID", this.vmId)
                .ticketMitigation()
                .toString(), exception);
    }

}
