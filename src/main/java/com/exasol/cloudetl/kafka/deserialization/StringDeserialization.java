package com.exasol.cloudetl.kafka.deserialization;

import org.apache.kafka.common.serialization.Deserializer;

import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

public final class StringDeserialization {
    private StringDeserialization() {
    }

    public static Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getDeserializer(
            final KafkaConsumerProperties properties, final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs) {
        return StringDeserializationImpl.getDeserializer(properties, fieldSpecs);
    }
}
