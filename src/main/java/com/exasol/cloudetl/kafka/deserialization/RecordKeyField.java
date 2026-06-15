package com.exasol.cloudetl.kafka.deserialization;

import java.util.Objects;

public final class RecordKeyField extends ConcreteField implements KeySpecification {
    public RecordKeyField(final String fieldName) {
        super(fieldName);
    }

    public RecordKeyField copy(final String fieldName) {
        return new RecordKeyField(fieldName);
    }

    @SuppressWarnings("java:S100") // Name required for Scala compatibility. Will be removed once tests are migrated to Java.
    public String copy$default$1() {
        return fieldName();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof RecordKeyField) && Objects.equals(fieldName(), ((RecordKeyField) other).fieldName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName());
    }

    @Override
    public String toString() {
        return "RecordKeyField(" + fieldName() + ")";
    }
}
