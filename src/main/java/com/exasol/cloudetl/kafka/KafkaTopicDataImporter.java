package com.exasol.cloudetl.kafka;

import java.util.ArrayList;
import java.util.List;

import com.exasol.ExaIterator;
import com.exasol.ExaMetadata;
import com.exasol.cloudetl.kafka.consumer.KafkaRecordConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KafkaTopicDataImporter {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicDataImporter.class);
    private static final int PARTITION_ID_INDEX = 1;
    private static final int MAXIMUM_OFFSET_INDEX = 2;

    private KafkaTopicDataImporter() {
    }

    public static void run(final ExaMetadata metadata, final ExaIterator iterator) throws Exception {
        final KafkaConsumerProperties kafkaProperties = KafkaConsumerProperties.apply(
                iterator.getString(KafkaConnectorConstants.KEY_VALUE_PROPERTIES_INDEX), metadata);
        final int partitionId = iterator.getInteger(PARTITION_ID_INDEX);
        final long partitionNextOffset = iterator.getLong(MAXIMUM_OFFSET_INDEX) + 1L;
        final int outputColumnCount = (int) metadata.getOutputColumnCount();
        final List<Class<?>> outputColumnTypes = new ArrayList<>();
        for (int columnIndex = 0; columnIndex < outputColumnCount; columnIndex++) {
            outputColumnTypes.add(metadata.getOutputColumnType(columnIndex));
        }
        final long nodeId = metadata.getNodeId();
        final String vmId = metadata.getVmId();
        LOGGER.info("Starting Kafka consumer for partition '{}' at next offset '{}' for node '{}' and vm '{}'.",
                partitionId, partitionNextOffset, nodeId, vmId);
        new KafkaRecordConsumer(kafkaProperties, partitionId, partitionNextOffset, ScalaCollections.seq(outputColumnTypes),
                outputColumnCount, nodeId, vmId).emit(iterator);
    }
}
