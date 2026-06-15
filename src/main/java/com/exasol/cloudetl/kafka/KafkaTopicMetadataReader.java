package com.exasol.cloudetl.kafka;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.exasol.ExaDataTypeException;
import com.exasol.ExaIterationException;
import com.exasol.ExaIterator;
import com.exasol.ExaMetadata;
import com.exasol.errorreporting.ExaError;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.AuthenticationException;
import org.apache.kafka.common.errors.AuthorizationException;
import org.apache.kafka.common.errors.TimeoutException;
import org.apache.kafka.common.serialization.VoidDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class KafkaTopicMetadataReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaTopicMetadataReader.class);
    private static final int PREVIOUS_PARTITION_ID_INDEX = 1;
    private static final int PREVIOUS_OFFSET_INDEX = 2;

    private KafkaTopicMetadataReader() {
    }

    public static void run(final ExaMetadata metadata, final ExaIterator iterator) throws Exception {
        final KafkaConsumerProperties kafkaProperties = KafkaConsumerProperties.apply(
                iterator.getString(KafkaConnectorConstants.KEY_VALUE_PROPERTIES_INDEX), metadata);
        final KafkaConsumer<Void, Void> kafkaConsumer = KafkaConsumerFactory.apply(kafkaProperties,
                new VoidDeserializer(), new VoidDeserializer());
        emitMetadata(iterator, kafkaConsumer, kafkaProperties.getTopic());
    }

    private static <K, V> void emitMetadata(final ExaIterator iterator, final KafkaConsumer<K, V> consumer,
            final String topic) throws Exception {
        final HashMap<Integer, Long> seenPartitionOffsets = getPreviousPartitionOffsets(iterator);
        final List<Integer> topicPartitionIds = new ArrayList<>();
        for (final Object partitionId : ScalaCollections.javaList(getTopicPartitions(consumer, topic))) {
            topicPartitionIds.add((Integer) partitionId);
        }
        emitTopicPartitionOffsets(iterator, consumer, topic, topicPartitionIds, seenPartitionOffsets);
    }

    private static HashMap<Integer, Long> getPreviousPartitionOffsets(final ExaIterator iterator) throws Exception {
        final HashMap<Integer, Long> partitionOffsets = new HashMap<>();
        do {
            partitionOffsets.put(iterator.getInteger(PREVIOUS_PARTITION_ID_INDEX),
                    iterator.getLong(PREVIOUS_OFFSET_INDEX));
        } while (iterator.next());
        return partitionOffsets;
    }

    public static <K, V> scala.collection.immutable.List<Object> getTopicPartitions(
            final KafkaConsumer<K, V> consumer, final String topic) {
        final List<Object> partitions = new ArrayList<>();
        try {
            consumer.partitionsFor(topic).forEach(partitionInfo -> partitions.add(partitionInfo.partition()));
            return ScalaCollections.list(partitions);
        } catch (final TimeoutException exception) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-24")
                    .message(KafkaConnectorConstants.ERROR_READING_TOPIC_PARTITION, topic)
                    .message(KafkaConnectorConstants.TIMEOUT_ERROR_MESSAGE)
                    .mitigation(KafkaConnectorConstants.TIMEOUT_ERROR_MITIGATION_1)
                    .mitigation(KafkaConnectorConstants.TIMEOUT_ERROR_MITIGATION_2)
                    .toString(), exception);
        } catch (final AuthorizationException exception) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-25")
                    .message(KafkaConnectorConstants.ERROR_READING_TOPIC_PARTITION, topic)
                    .message(KafkaConnectorConstants.AUTHORIZATION_ERROR_MESSAGE)
                    .parameter("CAUSE", exception.getMessage())
                    .mitigation(KafkaConnectorConstants.AUTHORIZATION_ERROR_MITIGATION)
                    .toString(), exception);
        } catch (final AuthenticationException exception) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-26")
                    .message(KafkaConnectorConstants.ERROR_READING_TOPIC_PARTITION, topic)
                    .message(KafkaConnectorConstants.AUTHENTICATION_ERROR_MESSAGE)
                    .parameter("CAUSE", exception.getMessage())
                    .mitigation(KafkaConnectorConstants.AUTHENTICATION_ERROR_MITIGATION)
                    .toString(), exception);
        } catch (final Throwable exception) {
            throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-27")
                    .message(KafkaConnectorConstants.ERROR_READING_TOPIC_PARTITION, topic)
                    .ticketMitigation()
                    .toString(), exception);
        } finally {
            consumer.close();
        }
    }

    private static <K, V> void emitTopicPartitionOffsets(final ExaIterator iterator, final KafkaConsumer<K, V> consumer,
            final String topic, final List<Integer> partitionIds, final HashMap<Integer, Long> offsets) {
        LOGGER.info("Emitting metadata for '{}' topic partitions", partitionIds);
        try {
            for (final int partitionId : partitionIds) {
                iterator.emit(Integer.valueOf(partitionId), offsets.getOrDefault(partitionId, -1L));
            }
        } catch (final ExaIterationException exception) {
            throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-2")
                    .message("Error iterating Exasol metadata iterator for topic {{TOPIC}}.", topic)
                    .mitigation("Please check that source Exasol table is available.")
                    .toString(), exception);
        } catch (final ExaDataTypeException exception) {
            throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-3")
                    .message("Error emitting metadata information for topic {{TOPIC}}.", topic)
                    .mitigation("Please check that Exasol metadata script contains output columns.")
                    .toString(), exception);
        } finally {
            consumer.close();
        }
    }
}
