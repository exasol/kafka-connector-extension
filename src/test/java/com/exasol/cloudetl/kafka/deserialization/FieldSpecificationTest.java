package com.exasol.cloudetl.kafka.deserialization;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

class FieldSpecificationTest {
    @Test
    void recordKeyFieldEqualsAndHashCodeFollowContract() {
        EqualsVerifier.forClass(RecordKeyField.class).verify();
    }

    @Test
    void recordValueFieldEqualsAndHashCodeFollowContract() {
        EqualsVerifier.forClass(RecordValueField.class).verify();
    }

    @Test
    void timestampFieldBehavesAsSingletonForEqualsAndHashCode() {
        assertEquals(TimestampField.INSTANCE, TimestampField.INSTANCE);
        assertEquals(TimestampField.INSTANCE.hashCode(), TimestampField.INSTANCE.hashCode());
        assertNotEquals(null, TimestampField.INSTANCE);
    }
}
