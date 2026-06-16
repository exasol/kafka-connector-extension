package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
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

        assertAll(() -> assertThat(thrown.getMessage(), containsString("E-KCE-17")),
                () -> assertThat(thrown.getMessage(), containsString("Schema Registry URL is missing")));
    }
}
