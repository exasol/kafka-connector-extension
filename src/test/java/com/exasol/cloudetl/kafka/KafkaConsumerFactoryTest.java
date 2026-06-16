package com.exasol.cloudetl.kafka;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.mockito.Mockito.mock;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.VoidDeserializer;
import org.junit.jupiter.api.Test;

import com.exasol.ExaMetadata;

class KafkaConsumerFactoryTest {
    @Test
    void applyReturnsDefaultKafkaConsumerType() {
        final var defaultProperties = map(entry("BOOTSTRAP_SERVERS", "localhost:6001"),
                entry("SCHEMA_REGISTRY_URL", "http://localhost:6002"), entry("TOPIC_NAME", "topic"),
                entry("TABLE_NAME", "exasolTable"));
        final var consumerProperties = KafkaConsumerPropertiesSupport.create(defaultProperties, mock(ExaMetadata.class));

        final var kafkaConsumer = KafkaConsumerFactory.apply(consumerProperties, new VoidDeserializer(),
                new VoidDeserializer());

        assertInstanceOf(KafkaConsumer.class, kafkaConsumer);
    }
}
