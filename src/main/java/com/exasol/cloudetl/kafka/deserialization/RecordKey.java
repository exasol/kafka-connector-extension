package com.exasol.cloudetl.kafka.deserialization;

public enum RecordKey implements KeySpecification, FullRecord {
    INSTANCE;

    @Override
    public String toString() {
        return "RecordKey";
    }
}
