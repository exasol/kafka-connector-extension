package com.exasol.cloudetl.kafka;

import com.exasol.errorreporting.ExaError;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.KafkaException;
import org.apache.kafka.common.serialization.Deserializer;

public final class KafkaConsumerFactory {
    private KafkaConsumerFactory() {
    }

    public static <K, V> KafkaConsumer<K, V> apply(final KafkaConsumerProperties properties,
            final Deserializer<K> keyDeserializer, final Deserializer<V> valueDeserializer) {
        final String topic = properties.getTopic();
        try {
            return new KafkaConsumer<>(properties.getProperties(), keyDeserializer, valueDeserializer);
        } catch (final KafkaException exception) {
            throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-1")
                    .message("Could not create a Kafka consumer for topic {{TOPIC}}.", topic)
                    .ticketMitigation()
                    .toString(), exception);
        }
    }
}
