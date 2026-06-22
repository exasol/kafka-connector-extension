package com.exasol.cloudetl.kafka.deserialization;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

import nl.jqno.equalsverifier.EqualsVerifier;
class DeserializationFactoryTest {
    @Test
    void providesDefaultSerializersStringKeyAndAvroValueWhenFieldsAreNotSpecified() {
        final var properties = new KafkaConsumerProperties(Map.of("SCHEMA_REGISTRY_URL", "someUrl"));
        final var deserializers = DeserializationFactory
                .getSerializers(List.of(RecordKey.INSTANCE, RecordValueFields.INSTANCE), properties);

        assertInstanceOf(AsStringDeserializer.class, deserializers.keyDeserializer);
        assertInstanceOf(GenericRecordDeserializer.class, deserializers.valueDeserializer);
    }

    @Test
    void doesNotDeserializeKeyWhenNotSpecifiedInFieldSpecs() {
        final var properties = new KafkaConsumerProperties(Map.of("RECORD_FORMAT", "json"));
        final var deserializers = DeserializationFactory.getSerializers(List.of(RecordValue.INSTANCE), properties);

        assertSame(IgnoreKeyDeserializer.INSTANCE, deserializers.keyDeserializer);
        assertInstanceOf(JsonDeserializer.class, deserializers.valueDeserializer);
    }

    @Test
    void ignoresKeyWhenNotRequested() {
        final var properties = new KafkaConsumerProperties(
                Map.of("RECORD_KEY_FORMAT", "avro", "RECORD_VALUE_FORMAT", "string"));
        final var deserializers = DeserializationFactory.getSerializers(List.of(RecordValue.INSTANCE), properties);

        assertSame(IgnoreKeyDeserializer.INSTANCE, deserializers.keyDeserializer);
        assertInstanceOf(AsStringDeserializer.class, deserializers.valueDeserializer);
    }

    @Test
    void takesKeyIntoAccountWhenRequested() {
        final var properties = new KafkaConsumerProperties(
                Map.of("RECORD_KEY_FORMAT", "json", "RECORD_VALUE_FORMAT", "string"));
        final var deserializers = DeserializationFactory
                .getSerializers(List.of(new RecordKeyField("someField"), RecordValue.INSTANCE), properties);

        assertInstanceOf(JsonDeserializer.class, deserializers.keyDeserializer);
        assertInstanceOf(AsStringDeserializer.class, deserializers.valueDeserializer);
    }

    @Test
    void failsForUnsupportedRecordFormats() {
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> DeserializationFactory.getDeserialization("protobuf"));

        assertAll(() -> assertThat(thrown.getMessage(), containsString("E-KCE-19")),
                () -> assertThat(thrown.getMessage(), containsString("not supported")));
    }

    @Test
    void recordDeserializersEqualsAndHashCodeFollowContract() {
        final Deserializer<Map<FieldSpecification, List<Object>>> red = IgnoreKeyDeserializer.INSTANCE;
        final Deserializer<Map<FieldSpecification, List<Object>>> black = new JsonDeserializer(
                List.of(new RecordValueField("field")), new StringDeserializer());

        EqualsVerifier.forClass(DeserializationFactory.RecordDeserializers.class)
                .withPrefabValues(Deserializer.class, red, black)
                .verify();
    }
}
