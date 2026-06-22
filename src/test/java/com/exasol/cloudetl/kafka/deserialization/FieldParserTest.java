package com.exasol.cloudetl.kafka.deserialization;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

class FieldParserTest {
    @Test
    void parsesListOfFieldSpecsToCorrectFields() {
        assertEquals(List.of(RecordKey.INSTANCE, RecordValueFields.INSTANCE), FieldParser.get(List.of("key", "value.*")));
    }

    @Test
    void parsesFieldSpecStringToCorrectFields() {
        assertEquals(List.of(RecordKey.INSTANCE, RecordValueFields.INSTANCE), FieldParser.get("key, value.*"));
    }

    @Test
    void parsesToCorrectFieldsWithTimestampAndSelect() {
        final var fields = List.of("key.field1", "timestamp", "value.*", "value.field2", "value.Field_3");

        assertEquals(List.of(new RecordKeyField("field1"), TimestampField.INSTANCE, RecordValueFields.INSTANCE,
                new RecordValueField("field2"), new RecordValueField("Field_3")), FieldParser.get(fields));
    }

    @Test
    void parsesToCorrectFieldsForSupportedNames() {
        final String fields = "key._key1, value.FirstValue, value.secondValue, value.value_3";

        assertEquals(List.of(new RecordKeyField("_key1"), new RecordValueField("FirstValue"),
                new RecordValueField("secondValue"), new RecordValueField("value_3")), FieldParser.get(fields));
    }

    @Test
    void failsForInvalidFieldReferences() {
        final Exception thrown = assertThrows(Exception.class, () -> FieldParser.get(List.of("metadata.partition")));

        assertAll(() -> assertThat(thrown.getMessage(), containsString("E-KCE-14")),
                () -> assertThat(thrown.getMessage(), containsString("does not have the correct format")));
    }
}
