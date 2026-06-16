package com.exasol.cloudetl.kafka.deserialization;

import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

import com.exasol.cloudetl.kafka.ScalaCollections;

public enum IgnoreKeyDeserializer
        implements Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> {
    INSTANCE;

    @Override
    public scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> deserialize(
            final String topic, final byte[] data) {
        return ScalaCollections.immutableMap(Map.of());
    }
}
