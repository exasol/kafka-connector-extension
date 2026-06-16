package com.exasol.cloudetl.kafka.deserialization;

public enum TimestampField implements GlobalFieldSpecification {
    INSTANCE;

    @Override
    public String toString() {
        return "TimestampField";
    }
}
