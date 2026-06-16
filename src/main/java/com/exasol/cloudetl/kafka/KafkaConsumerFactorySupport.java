package com.exasol.cloudetl.kafka;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.Deserializer;

public final class KafkaConsumerFactorySupport {
    private KafkaConsumerFactorySupport() {
    }

    public static <K, V> KafkaConsumer<K, V> create(final KafkaConsumerProperties properties,
            final Deserializer<K> keyDeserializer, final Deserializer<V> valueDeserializer) {
        return KafkaConsumerFactory.apply(properties, keyDeserializer, valueDeserializer);
    }
}
