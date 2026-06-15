package com.exasol.cloudetl.kafka.deserialization;

import java.util.Objects;

public final class RecordValueField extends ConcreteField implements ValueSpecification {
    public RecordValueField(final String fieldName) {
        super(fieldName);
    }

    public RecordValueField copy(final String fieldName) {
        return new RecordValueField(fieldName);
    }

    @SuppressWarnings("java:S100") // Name required for Scala compatibility. Will be removed once tests are migrated to Java.
    public String copy$default$1() {
        return fieldName();
    }

    @Override
    public boolean equals(final Object other) {
        return (other instanceof RecordValueField)
                && Objects.equals(fieldName(), ((RecordValueField) other).fieldName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(fieldName());
    }

    @Override
    public String toString() {
        return "RecordValueField(" + fieldName() + ")";
    }
}
