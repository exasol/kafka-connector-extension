package com.exasol.cloudetl.kafka.deserialization;

public abstract class ConcreteField implements FieldSpecification {
    private final String fieldName;

    protected ConcreteField(final String fieldName) {
        this.fieldName = fieldName;
    }

    public String fieldName() {
        return this.fieldName;
    }

    public String getFieldName() {
        return this.fieldName;
    }
}
