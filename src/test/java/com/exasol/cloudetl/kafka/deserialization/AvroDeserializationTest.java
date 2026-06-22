package com.exasol.cloudetl.kafka.deserialization;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

class AvroDeserializationTest {
    @Test
    void createsGenericRecordDeserializerWhenSchemaRegistryIsConfigured() {
        final var properties = new KafkaConsumerProperties(Map.of("SCHEMA_REGISTRY_URL", "http://schema-registry:8081"));

        final var deserializer = AvroDeserialization.getDeserializer(properties, List.of(new RecordValueField("amount")));

        assertInstanceOf(GenericRecordDeserializer.class, deserializer);
    }

    @Test
    void failsWhenSchemaRegistryIsMissing() {
        final var properties = new KafkaConsumerProperties(Map.of());
        final List<FieldSpecification> fields = List.of(new RecordValueField("amount"));

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> AvroDeserialization.getDeserializer(properties, fields));

        assertAll(() -> assertThat(thrown.getMessage(), containsString("E-KCE-17")),
                () -> assertThat(thrown.getMessage(), containsString("Schema Registry URL is missing")));
    }
}
