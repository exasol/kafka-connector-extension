package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.*;

class AvroDeserializationTest {
    @Test
    void createsGenericRecordDeserializerWhenSchemaRegistryIsConfigured() {
        final var properties = new KafkaConsumerProperties(map(entry("SCHEMA_REGISTRY_URL", "http://schema-registry:8081")));

        final var deserializer = AvroDeserialization.getDeserializer(properties, seq(new RecordValueField("amount")));

        assertInstanceOf(GenericRecordDeserializer.class, deserializer);
    }

    @Test
    void failsWhenSchemaRegistryIsMissing() {
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> AvroDeserialization.getDeserializer(new KafkaConsumerProperties(map()),
                        seq(new RecordValueField("amount"))));

        assertAll(() -> assertTrue(thrown.getMessage().contains("E-KCE-17")),
                () -> assertTrue(thrown.getMessage().contains("Schema Registry URL is missing")));
    }
}
