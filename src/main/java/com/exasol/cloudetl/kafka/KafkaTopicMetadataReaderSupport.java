package com.exasol.cloudetl.kafka;

import com.exasol.ExaDataTypeException;
import com.exasol.ExaIterator;
import com.exasol.ExaIterationException;
import com.exasol.ExaMetadata;

import org.apache.kafka.clients.consumer.KafkaConsumer;

public final class KafkaTopicMetadataReaderSupport {
    private KafkaTopicMetadataReaderSupport() {
    }

    public static void run(final ExaMetadata metadata, final ExaIterator iterator)
            throws ExaIterationException, ExaDataTypeException {
        KafkaTopicMetadataReader.run(metadata, iterator);
    }

    public static <K, V> scala.collection.immutable.List<Object> getTopicPartitions(final KafkaConsumer<K, V> consumer,
            final String topic) {
        return KafkaTopicMetadataReader.getTopicPartitions(consumer, topic);
    }
}
