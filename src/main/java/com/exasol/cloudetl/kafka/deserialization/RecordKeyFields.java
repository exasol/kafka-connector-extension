package com.exasol.cloudetl.kafka.deserialization;

public enum RecordKeyFields implements KeySpecification, AllFieldsSpecification {
    INSTANCE;

    @Override
    public String toString() {
        return "RecordKeyFields";
    }
}
