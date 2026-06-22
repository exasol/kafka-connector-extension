package com.exasol.cloudetl.kafka.deserialization;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

public enum IgnoreKeyDeserializer implements Deserializer<Map<FieldSpecification, List<Object>>> {
    INSTANCE;

    @Override
    public Map<FieldSpecification, List<Object>> deserialize(final String topic, final byte[] data) {
        return Map.of();
    }
}
