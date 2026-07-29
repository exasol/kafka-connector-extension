package com.exasol.cloudetl.kafka.consumer;

import java.time.Duration;
import java.util.*;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.exasol.*;
import com.exasol.cloudetl.kafka.*;
import com.exasol.cloudetl.kafka.deserialization.*;
import com.exasol.errorreporting.ExaError;

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

    private KafkaRecordConsumer(final Builder builder) {
        this.properties = builder.properties;
        this.partitionId = builder.partitionId;
        this.partitionStartOffset = builder.partitionStartOffset;
        this.outputColumnTypes = builder.outputColumnTypes;
        this.tableColumnCount = builder.tableColumnCount;
        this.nodeId = builder.nodeId;
        this.vmId = builder.vmId;
        this.topic = this.properties.getTopic();
        this.consumer = builder.consumer == null
                ? createRecordConsumer(this.properties, this.partitionId, this.partitionStartOffset)
                : builder.consumer;
        this.partitionEndOffset = getPartitionEndOffset();
        this.maxRecordsPerRun = this.properties.getMaxRecordsPerRun();
        this.minRecordsPerRun = this.properties.getMinRecordsPerRun();
        this.timeout = Duration.ofMillis(this.properties.getPollTimeoutMs());
        this.recordFieldSpecifications = FieldParser.get(this.properties.getRecordFields());
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private KafkaConsumerProperties properties;
        private int partitionId;
        private long partitionStartOffset;
        private List<Class<?>> outputColumnTypes;
        private int tableColumnCount;
        private long nodeId;
        private String vmId;
        private KafkaConsumer<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> consumer;

        public Builder withProperties(final KafkaConsumerProperties properties) {
            this.properties = Objects.requireNonNull(properties);
            return this;
        }

        public Builder withPartitionId(final int partitionId) {
            this.partitionId = partitionId;
            return this;
        }

        public Builder withPartitionStartOffset(final long partitionStartOffset) {
            this.partitionStartOffset = partitionStartOffset;
            return this;
        }

        public Builder withOutputColumnTypes(final List<Class<?>> outputColumnTypes) {
            this.outputColumnTypes = Objects.requireNonNull(outputColumnTypes);
            return this;
        }

        public Builder withTableColumnCount(final int tableColumnCount) {
            this.tableColumnCount = tableColumnCount;
            return this;
        }

        public Builder withNodeId(final long nodeId) {
            this.nodeId = nodeId;
            return this;
        }

        public Builder withVmId(final String vmId) {
            this.vmId = Objects.requireNonNull(vmId);
            return this;
        }

        Builder withConsumer(final KafkaConsumer<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> consumer) {
            this.consumer = Objects.requireNonNull(consumer);
            return this;
        }

        public KafkaRecordConsumer build() {
            return new KafkaRecordConsumer(this);
        }
    }

    @Override
    public final void emit(final ExaIterator iterator) {
        long recordOffset = this.partitionStartOffset;
        int recordCount;
        long totalRecordCount = 0L;
        try {
            do {
                final ConsumerRecords<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> records = this.consumer.poll(this.timeout);
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

    private static KafkaConsumer<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> createRecordConsumer(
            final KafkaConsumerProperties properties, final int partitionId, final long partitionStartOffset) {
        final TopicPartition topicPartition = new TopicPartition(properties.getTopic(), partitionId);
        final List<GlobalFieldSpecification> recordFields = FieldParser.get(properties.getRecordFields());
        final DeserializationFactory.RecordDeserializers recordDeserializers = DeserializationFactory.getSerializers(recordFields, properties);
        final KafkaConsumer<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> newConsumer = KafkaConsumerFactory.apply(properties,
                recordDeserializers.getKeyDeserializer(),
                recordDeserializers.getValueDeserializer());
        newConsumer.assign(Arrays.asList(topicPartition));
        newConsumer.seek(topicPartition, partitionStartOffset);
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
