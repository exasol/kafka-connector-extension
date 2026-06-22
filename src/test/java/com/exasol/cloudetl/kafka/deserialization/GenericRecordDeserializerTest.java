package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.*;

import org.apache.avro.Schema;
import org.apache.avro.SchemaBuilder;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.generic.GenericRecordBuilder;
import org.apache.kafka.common.serialization.Deserializer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.cloudetl.kafka.JsonArgumentMatcher;

import scala.collection.immutable.Seq;

@ExtendWith(MockitoExtension.class)
class GenericRecordDeserializerTest {
    private final Schema schema = SchemaBuilder.record("test").fields()
            .optionalString("field1")
            .requiredLong("field2")
            .name("complex").type(SchemaBuilder.array().items().intType()).withDefault(Collections.emptyList())
            .endRecord();

    @Mock
    Deserializer<GenericRecord> delegateMock;

    private Map<FieldSpecification, List<Object>> extractFrom(
            final GenericRecord genericRecord, final Seq<FieldSpecification> fieldList) {
        when(delegateMock.deserialize(anyString(), any(byte[].class))).thenReturn(genericRecord);
        return new GenericRecordDeserializer(fieldList, delegateMock).deserialize("", new byte[0]);
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
        final var values = javaList(javaRow.get(RecordValue.INSTANCE));
        final String expectedJson = "{\"field1\":\"val1\",\"field2\":11,\"complex\":[1,2,3]}";
        assertAll(() -> assertEquals(1, javaRow.size()),
                () -> assertTrue(javaRow.containsKey(RecordValue.INSTANCE)),
                () -> assertEquals(1, values.size()),
                () -> assertInstanceOf(String.class, values.get(0)),
                () -> assertEquals(JsonArgumentMatcher.readJson(expectedJson),
                        JsonArgumentMatcher.readJson((String) values.get(0))));
    }
}
