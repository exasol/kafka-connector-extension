package com.exasol.cloudetl.kafka.deserialization;

public enum RecordValueFields implements ValueSpecification, AllFieldsSpecification {
    INSTANCE;

    @Override
    public String toString() {
        return "RecordValueFields";
    }
}
