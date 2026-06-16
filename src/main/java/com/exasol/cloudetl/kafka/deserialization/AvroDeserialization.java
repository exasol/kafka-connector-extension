package com.exasol.cloudetl.kafka.deserialization;

import org.apache.kafka.common.serialization.Deserializer;

import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

public final class AvroDeserialization {
    private AvroDeserialization() {
    }

    public static Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        return AvroDeserializationImpl.getDeserializer(properties, fieldSpecs);
    }
}
