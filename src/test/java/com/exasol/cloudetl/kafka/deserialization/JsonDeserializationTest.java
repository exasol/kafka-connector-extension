package com.exasol.cloudetl.kafka.deserialization;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

class JsonDeserializationTest {
    @Test
    void createsJsonDeserializerForConcreteFields() {
        final var deserializer = JsonDeserialization.getDeserializer(new KafkaConsumerProperties(Map.of()),
                List.of(new RecordValueField("user_id")));

        assertInstanceOf(JsonDeserializer.class, deserializer);
    }

    @Test
    void rejectsWildcardFieldSelectionForJson() {
        final var properties = new KafkaConsumerProperties(Map.of());
        final List<FieldSpecification> fields = List.of(RecordValueFields.INSTANCE);

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> JsonDeserialization.getDeserializer(properties, fields));

        assertAll(() -> assertThat(thrown.getMessage(), containsString("E-KCE-16")),
                () -> assertThat(thrown.getMessage(), containsString("not supported for JSON")));
    }
}
