package com.exasol.cloudetl.kafka.deserialization;

import java.util.Map;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.KafkaConsumerProperties;
import com.exasol.errorreporting.ExaError;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;

public final class AvroDeserialization {
    private AvroDeserialization() {
    }

    public static Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        if (properties.hasSchemaRegistryUrl()) {
            return new GenericRecordDeserializer(fieldSpecs, getAvroDeserializer(properties.getSchemaRegistryUrl()));
        }
        throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-17")
                .message("Required Schema Registry URL is missing for Avro records.")
                .mitigation("Please provide URL using SCHEMA_REGISTRY_URL property.")
                .toString());
    }

    @SuppressWarnings("unchecked")
    private static Deserializer<GenericRecord> getAvroDeserializer(final String schemaRegistryUrl) {
        final KafkaAvroDeserializer kafkaAvroDeserializer = new KafkaAvroDeserializer();
        kafkaAvroDeserializer.configure(Map.of(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG,
                schemaRegistryUrl), false);
        return (Deserializer<GenericRecord>) (Deserializer<?>) kafkaAvroDeserializer;
    }
}
