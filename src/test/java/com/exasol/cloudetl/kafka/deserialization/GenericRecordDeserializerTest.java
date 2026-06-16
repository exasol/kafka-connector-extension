package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.*;
import org.apache.kafka.common.serialization.Deserializer;
import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.JsonArgumentMatcher;

class GenericRecordDeserializerTest {
    private final Schema schema = SchemaBuilder.record("test").fields()
            .optionalString("field1")
            .requiredLong("field2")
            .name("complex").type(SchemaBuilder.array().items().intType()).withDefault(Collections.emptyList())
            .endRecord();

    private scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> extractFrom(
            final GenericRecord record, final scala.collection.immutable.Seq<FieldSpecification> fieldList) {
        @SuppressWarnings("unchecked")
        final Deserializer<GenericRecord> delegate = mock(Deserializer.class);
        when(delegate.deserialize(anyString(), any(byte[].class))).thenReturn(record);
        return new GenericRecordDeserializer(fieldList, delegate).deserialize("", new byte[0]);
    }

    @Test
    void givesAllFieldsFromRecordIfFieldListIsNotProvided() {
        final var row = extractFrom(new GenericRecordBuilder(this.schema)
                .set("field1", "val1").set("field2", 11L).set("complex", new Integer[] { 1, 2, 3 }).build(),
                seq(RecordValueFields.INSTANCE));

        assertMapOfSeqEquals(Map.of(RecordValueFields.INSTANCE, List.of("val1", 11L, "[1,2,3]")), row);
    }

    @Test
    void onlyUsesFieldsProvidedToDeserializerInTheRightOrder() {
        final var row = extractFrom(new GenericRecordBuilder(this.schema)
                .set("field1", "val1").set("field2", 11L).set("complex", new Integer[] { 1, 2, 3 }).build(),
                seq(new RecordValueField("complex"), new RecordValueField("field1")));

        assertMapOfSeqEquals(Map.of(new RecordValueField("complex"), List.of("[1,2,3]"),
                new RecordValueField("field1"), List.of("val1")), row);
    }

    @Test
    void providesNullValuesForFieldsNotPresentAndDefaultValues() {
        final var row = extractFrom(new GenericRecordBuilder(this.schema).set("field2", 11L).build(),
                seq(new RecordValueField("field1"), new RecordValueField("field2"), new RecordValueField("complex")));

        final Map<FieldSpecification, List<Object>> expected = new LinkedHashMap<>();
        expected.put(new RecordValueField("field1"), Collections.singletonList(null));
        expected.put(new RecordValueField("field2"), List.of(11L));
        expected.put(new RecordValueField("complex"), List.of("[]"));
        assertMapOfSeqEquals(expected, row);
    }

    @Test
    void returnsNullForNonExistentFieldToKeepTableStructure() {
        final var row = extractFrom(new GenericRecordBuilder(this.schema).set("field2", 11L).build(),
                seq(new RecordValueField("field2"), new RecordValueField("unknownField")));

        final Map<FieldSpecification, List<Object>> expected = new LinkedHashMap<>();
        expected.put(new RecordValueField("field2"), List.of(11L));
        expected.put(new RecordValueField("unknownField"), Collections.singletonList(null));
        assertMapOfSeqEquals(expected, row);
    }

    @Test
    void serializesRecordAsFullJsonWhenRequested() {
        final var row = extractFrom(new GenericRecordBuilder(this.schema)
                .set("field1", "val1").set("field2", 11L).set("complex", new Integer[] { 1, 2, 3 }).build(),
                seq(RecordValue.INSTANCE));

        final var javaRow = javaMap(row);
        assertEquals(1, javaRow.size());
        assertTrue(javaRow.containsKey(RecordValue.INSTANCE));
        final var values = javaList(javaRow.get(RecordValue.INSTANCE));
        assertEquals(1, values.size());
        assertInstanceOf(String.class, values.get(0));

        final String expectedJson = "{\"field1\":\"val1\",\"field2\":11,\"complex\":[1,2,3]}";
        assertEquals(JsonArgumentMatcher.readJson(expectedJson), JsonArgumentMatcher.readJson((String) values.get(0)));
    }
}
