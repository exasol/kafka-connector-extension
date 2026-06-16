package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.*;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.JsonArgumentMatcher;

class JsonDeserializerTest {
    @Test
    void deserializesJsonRecordWithPrimitives() {
        final var row = deserialize(seq(new RecordValueField("number"), new RecordValueField("string"),
                new RecordValueField("bool")), "{\"number\":1,\"string\":\"hello\",\"bool\":true}");

        assertMapOfSeqEquals(Map.of(new RecordValueField("number"), List.of(1),
                new RecordValueField("string"), List.of("hello"),
                new RecordValueField("bool"), List.of(true)), row);
    }

    @Test
    void convertsComplexJsonTypeToStringRepresentation() {
        final var row = deserialize(seq(new RecordValueField("number"), new RecordValueField("record")),
                "{\"number\":1,\"record\":{\"field1\":\"value1\",\"field2\":23}}");

        assertMapOfSeqEquals(Map.of(new RecordValueField("number"), List.of(1),
                new RecordValueField("record"), List.of("{\"field1\":\"value1\",\"field2\":23}")), row);
    }

    @Test
    void onlyUsesFieldsProvidedToDeserializerInTheRightOrder() {
        final var row = deserialize(seq(new RecordValueField("record"), new RecordValueField("number")),
                "{\"number\":1,\"fieldToIgnore\":{\"fieldA\":124,\"fieldB\":[true]},"
                        + "\"record\":{\"field1\":\"value1\",\"field2\":23}}");

        assertMapOfSeqEquals(Map.of(new RecordValueField("number"), List.of(1),
                new RecordValueField("record"), List.of("{\"field1\":\"value1\",\"field2\":23}")), row);
    }

    @Test
    void providesNullValuesForFieldsNotPresent() {
        final var row = deserialize(seq(new RecordValueField("number"), new RecordValueField("always_null_field")),
                "{\"number\":1}");

        final Map<FieldSpecification, List<Object>> expected = new LinkedHashMap<>();
        expected.put(new RecordValueField("number"), List.of(1));
        expected.put(new RecordValueField("always_null_field"), Collections.singletonList(null));
        assertMapOfSeqEquals(expected, row);
    }

    @Test
    void failsWhenAllFieldsAreReferenced() {
        assertThrows(KafkaConnectorException.class,
                () -> deserialize(seq(RecordValueFields.INSTANCE), "{\"number\":1}"));
    }

    @Test
    void producesFullJsonWhenWholeValueIsReferenced() {
        final String sourceRecord = "{\"number\":1,\"record\":{\"field1\":\"value1\",\"field2\":23}}";

        final var row = deserialize(seq(RecordValue.INSTANCE), sourceRecord);

        assertEquals(1, javaMap(row).size());
        assertTrue(javaMap(row).containsKey(RecordValue.INSTANCE));
        final var values = javaList(javaMap(row).get(RecordValue.INSTANCE));
        assertEquals(1, values.size());
        assertEquals(JsonArgumentMatcher.readJson(sourceRecord), JsonArgumentMatcher.readJson((String) values.get(0)));
    }

    private scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> deserialize(
            final scala.collection.immutable.Seq<FieldSpecification> fields, final String json) {
        return new JsonDeserializer(fields, new StringDeserializer()).deserialize("randomTopic",
                json.getBytes(StandardCharsets.UTF_8));
    }
}
