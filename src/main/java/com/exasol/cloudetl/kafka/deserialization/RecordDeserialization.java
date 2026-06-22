package com.exasol.cloudetl.kafka.deserialization;

import java.util.List;
import java.util.Map;

import com.exasol.cloudetl.kafka.KafkaConsumerProperties;

import org.apache.kafka.common.serialization.Deserializer;

public interface RecordDeserialization {
    Deserializer<Map<FieldSpecification, List<Object>>> getDeserializer(
            KafkaConsumerProperties properties, List<FieldSpecification> fieldSpecs);
}
