package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.map;
import static com.exasol.cloudetl.kafka.TestCollections.seq;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

import scala.collection.immutable.Seq;

class JsonDeserializationTest {
    @Test
    void createsJsonDeserializerForConcreteFields() {
        final var deserializer = JsonDeserialization.getDeserializer(new KafkaConsumerProperties(map()),
                seq(new RecordValueField("user_id")));

        assertInstanceOf(JsonDeserializer.class, deserializer);
    }

    @Test
    void rejectsWildcardFieldSelectionForJson() {
        final var properties = new KafkaConsumerProperties(map());
        final Seq<FieldSpecification> fields = seq(RecordValueFields.INSTANCE);

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> JsonDeserialization.getDeserializer(properties, fields));

        assertAll(() -> assertThat(thrown.getMessage(), containsString("E-KCE-16")),
                () -> assertThat(thrown.getMessage(), containsString("not supported for JSON")));
    }
}
