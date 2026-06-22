package com.exasol.cloudetl.kafka.deserialization;

import java.util.List;
import java.util.Map;

import org.apache.kafka.common.serialization.Deserializer;

final class FieldSpecificationSingletons {
    private FieldSpecificationSingletons() {
    }

    static GlobalFieldSpecification recordKey() {
        return RecordKey.INSTANCE;
    }

    static GlobalFieldSpecification recordValue() {
        return RecordValue.INSTANCE;
    }

    static GlobalFieldSpecification recordKeyFields() {
        return RecordKeyFields.INSTANCE;
    }

    static GlobalFieldSpecification recordValueFields() {
        return RecordValueFields.INSTANCE;
    }

    static GlobalFieldSpecification timestampField() {
        return TimestampField.INSTANCE;
    }

    static Deserializer<Map<FieldSpecification, List<Object>>> ignoreKeyDeserializer() {
        return IgnoreKeyDeserializer.INSTANCE;
    }
}
