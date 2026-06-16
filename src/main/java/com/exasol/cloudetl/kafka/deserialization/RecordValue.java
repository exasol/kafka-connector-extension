package com.exasol.cloudetl.kafka.deserialization;

public enum RecordValue implements ValueSpecification, FullRecord {
    INSTANCE;

    @Override
    public String toString() {
        return "RecordValue";
    }
}
