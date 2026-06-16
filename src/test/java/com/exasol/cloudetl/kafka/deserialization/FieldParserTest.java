package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.seq;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FieldParserTest {
    @Test
    void parsesListOfFieldSpecsToCorrectFields() {
        assertEquals(seq(RecordKey.INSTANCE, RecordValueFields.INSTANCE), FieldParser.get(seq("key", "value.*")));
    }

    @Test
    void parsesFieldSpecStringToCorrectFields() {
        assertEquals(seq(RecordKey.INSTANCE, RecordValueFields.INSTANCE), FieldParser.get("key, value.*"));
    }

    @Test
    void parsesToCorrectFieldsWithTimestampAndSelect() {
        final var fields = seq("key.field1", "timestamp", "value.*", "value.field2", "value.Field_3");

        assertEquals(seq(new RecordKeyField("field1"), TimestampField.INSTANCE, RecordValueFields.INSTANCE,
                new RecordValueField("field2"), new RecordValueField("Field_3")), FieldParser.get(fields));
    }

    @Test
    void parsesToCorrectFieldsForSupportedNames() {
        final String fields = "key._key1, value.FirstValue, value.secondValue, value.value_3";

        assertEquals(seq(new RecordKeyField("_key1"), new RecordValueField("FirstValue"),
                new RecordValueField("secondValue"), new RecordValueField("value_3")), FieldParser.get(fields));
    }

    @Test
    void failsForInvalidFieldReferences() {
        final Exception thrown = assertThrows(Exception.class, () -> FieldParser.get(seq("metadata.partition")));

        assertAll(() -> assertThat(thrown.getMessage(), containsString("E-KCE-14")),
                () -> assertThat(thrown.getMessage(), containsString("does not have the correct format")));
    }
}
