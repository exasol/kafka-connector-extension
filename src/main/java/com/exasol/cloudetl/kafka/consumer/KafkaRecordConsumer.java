package com.exasol.cloudetl.kafka.consumer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.exasol.ExaIterator;
import com.exasol.cloudetl.kafka.KafkaConnectorConstants;
import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerFactory;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;
import com.exasol.cloudetl.kafka.ScalaCollections;
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
    private final scala.collection.immutable.Seq<Class<?>> outputColumnTypes;
    private final int tableColumnCount;
    private final long nodeId;
    private final String vmId;
    private final String topic;
    private final KafkaConsumer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> consumer;
    private final long partitionEndOffset;
    private final int maxRecordsPerRun;
    private final int minRecordsPerRun;
    private final Duration timeout;
    private final scala.collection.immutable.Seq<GlobalFieldSpecification> recordFieldSpecifications;

    public KafkaRecordConsumer(final KafkaConsumerProperties properties, final int partitionId,
            final long partitionStartOffset, final scala.collection.immutable.Seq<Class<?>> outputColumnTypes,
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
                final ConsumerRecords<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> records = this.consumer.poll(this.timeout);
                recordCount = records.count();
                totalRecordCount += recordCount;
                recordOffset = updateRecordOffset(emitRecords(iterator, records));
                LOGGER.info("Polled '{}' records, total '{}' records for partition '{}' in node '{}' and vm '{}'.",
                        recordCount, totalRecordCount, this.partitionId, this.nodeId, this.vmId);
            } while (shouldContinue(recordOffset, recordCount, totalRecordCount));
        } catch (final Throwable exception) {
            handleExceptions(exception);
        } finally {
            this.consumer.close();
        }
    }

    private long updateRecordOffset(final long currentOffset) {
        return currentOffset == -1L ? getPartitionCurrentOffset() : currentOffset;
    }

    protected KafkaConsumer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getRecordConsumer() {
        final TopicPartition topicPartition = new TopicPartition(this.topic, this.partitionId);
        final scala.collection.immutable.Seq<GlobalFieldSpecification> recordFields =
                FieldParser.get(this.properties.getRecordFields());
        final DeserializationFactory.RecordDeserializers recordDeserializers =
                DeserializationFactory.getSerializers(recordFields, this.properties);
        final KafkaConsumer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> newConsumer = KafkaConsumerFactory.apply(this.properties,
                recordDeserializers.keyDeserializer(), recordDeserializers.valueDeserializer());
        newConsumer.assign(Arrays.asList(topicPartition));
        newConsumer.seek(topicPartition, this.partitionStartOffset);
        return newConsumer;
    }

    private long emitRecords(final ExaIterator iterator, final ConsumerRecords<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> records)
            throws Exception {
        long lastRecordOffset = -1L;
        final FieldConverter fieldConverter = new FieldConverter(this.outputColumnTypes);
        for (final ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> record : records) {
            lastRecordOffset = record.offset();
            final List<Object> metadata = List.of(record.partition(), record.offset());
            final int columnsCount = this.tableColumnCount - metadata.size();
            final List<Object> row = new ArrayList<>(
                    ScalaCollections.javaList(RowBuilder.buildRow(this.recordFieldSpecifications, record, columnsCount)));
            row.addAll(metadata);
            final scala.collection.immutable.Seq<Object> convertedRow =
                    fieldConverter.convertRow(ScalaCollections.seq(row));
            iterator.emit(ScalaCollections.javaList(convertedRow).toArray(new Object[0]));
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

    private void handleExceptions(final Throwable throwable) {
        if (throwable instanceof IllegalStateException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-20")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message("Consumer is not subscribed to the given topic or it is not assigned any partition of the topic.")
                    .mitigation("Please check that the Kafka topic is available and valid.")
                    .toString(), throwable);
        } else if (throwable instanceof InvalidTopicException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-21")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message("Provided topic is not valid.")
                    .mitigation("Please make sure that the Kafka topic is valid.")
                    .toString(), throwable);
        } else if (throwable instanceof AuthorizationException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-22")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message(KafkaConnectorConstants.AUTHORIZATION_ERROR_MESSAGE)
                    .parameter("CAUSE", throwable.getMessage())
                    .mitigation(KafkaConnectorConstants.AUTHORIZATION_ERROR_MITIGATION)
                    .toString(), throwable);
        } else if (throwable instanceof AuthenticationException) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-23")
                    .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                    .message(KafkaConnectorConstants.AUTHENTICATION_ERROR_MESSAGE)
                    .parameter("CAUSE", throwable.getMessage())
                    .mitigation(KafkaConnectorConstants.AUTHENTICATION_ERROR_MITIGATION)
                    .toString(), throwable);
        }
        throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-4")
                .message(KafkaConnectorConstants.ERROR_POLLING_TOPIC_DATA, this.topic)
                .message("It occurs for partition {{PARTITION_ID}} in node {{NODE_ID}} and vm {{VM_ID}}.")
                .parameter("PARTITION_ID", String.valueOf(this.partitionId))
                .parameter("NODE_ID", String.valueOf(this.nodeId))
                .parameter("VM_ID", this.vmId)
                .ticketMitigation()
                .toString(), throwable);
    }

}
