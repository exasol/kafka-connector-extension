package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

import nl.jqno.equalsverifier.EqualsVerifier;

class DeserializationFactoryTest {
    @Test
    void providesDefaultSerializersStringKeyAndAvroValueWhenFieldsAreNotSpecified() {
        final var properties = new KafkaConsumerProperties(map(entry("SCHEMA_REGISTRY_URL", "someUrl")));
        final var deserializers = DeserializationFactory
                .getSerializers(seq(RecordKey.INSTANCE, RecordValueFields.INSTANCE), properties);

        assertInstanceOf(AsStringDeserializer.class, deserializers.keyDeserializer);
        assertInstanceOf(GenericRecordDeserializer.class, deserializers.valueDeserializer);
    }

    @Test
    void doesNotDeserializeKeyWhenNotSpecifiedInFieldSpecs() {
        final var properties = new KafkaConsumerProperties(map(entry("RECORD_FORMAT", "json")));
        final var deserializers = DeserializationFactory.getSerializers(seq(RecordValue.INSTANCE), properties);

        assertSame(IgnoreKeyDeserializer.INSTANCE, deserializers.keyDeserializer);
        assertInstanceOf(JsonDeserializer.class, deserializers.valueDeserializer);
    }

    @Test
    void ignoresKeyWhenNotRequested() {
        final var properties = new KafkaConsumerProperties(
                map(entry("RECORD_KEY_FORMAT", "avro"), entry("RECORD_VALUE_FORMAT", "string")));
        final var deserializers = DeserializationFactory.getSerializers(seq(RecordValue.INSTANCE), properties);

        assertSame(IgnoreKeyDeserializer.INSTANCE, deserializers.keyDeserializer);
        assertInstanceOf(AsStringDeserializer.class, deserializers.valueDeserializer);
    }

    @Test
    void takesKeyIntoAccountWhenRequested() {
        final var properties = new KafkaConsumerProperties(
                map(entry("RECORD_KEY_FORMAT", "json"), entry("RECORD_VALUE_FORMAT", "string")));
        final var deserializers = DeserializationFactory
                .getSerializers(seq(new RecordKeyField("someField"), RecordValue.INSTANCE), properties);

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
        final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> red = IgnoreKeyDeserializer.INSTANCE;
        final Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> black = new JsonDeserializer(
                seq(new RecordValueField("field")), new StringDeserializer());

        EqualsVerifier.forClass(DeserializationFactory.RecordDeserializers.class)
                .withPrefabValues(Deserializer.class, red, black)
                .verify();
    }
}
