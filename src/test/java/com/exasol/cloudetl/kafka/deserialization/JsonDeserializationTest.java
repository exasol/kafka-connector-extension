package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.*;

class JsonDeserializationTest {
    @Test
    void createsJsonDeserializerForConcreteFields() {
        final var deserializer = JsonDeserialization.getDeserializer(new KafkaConsumerProperties(map()),
                seq(new RecordValueField("user_id")));

        assertInstanceOf(JsonDeserializer.class, deserializer);
    }

    @Test
    void rejectsWildcardFieldSelectionForJson() {
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> JsonDeserialization.getDeserializer(new KafkaConsumerProperties(map()),
                        seq(RecordValueFields.INSTANCE)));

        assertAll(() -> assertTrue(thrown.getMessage().contains("E-KCE-16")),
                () -> assertTrue(thrown.getMessage().contains("not supported for JSON")));
    }
}
