package com.exasol.cloudetl.kafka.deserialization;

import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

import org.apache.kafka.common.serialization.Deserializer;

public interface RecordDeserialization {
    Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getDeserializer(
            KafkaConsumerProperties properties, scala.collection.immutable.Seq<FieldSpecification> fieldSpecs);
}
