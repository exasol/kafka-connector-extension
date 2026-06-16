package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.Instant;
import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;

import com.exasol.cloudetl.kafka.KafkaConnectorException;

class RowBuilderTest {
    private static final long TIMESTAMP = Instant.now().toEpochMilli();

    @SuppressWarnings("unchecked")
    private ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> record(
            final scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> key,
            final scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> value) {
        final ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> record =
                mock(ConsumerRecord.class);
        when(record.key()).thenReturn(key);
        when(record.value()).thenReturn(value);
        when(record.timestamp()).thenReturn(TIMESTAMP);
        return record;
    }

    @Test
    void emitsRecordKeyAndValue() {
        assertSeqEquals(List.of("key", "value"), RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE),
                record(map(entry(RecordKey.INSTANCE, seq("key"))), map(entry(RecordValue.INSTANCE, seq("value")))), 0));
    }

    @Test
    void emitsRecordTimestampKeyAndValue() {
        assertSeqEquals(List.of("key", "value", TIMESTAMP),
                RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE, TimestampField.INSTANCE),
                        record(map(entry(RecordKey.INSTANCE, seq("key"))), map(entry(RecordValue.INSTANCE, seq("value")))), 0));
    }

    @Test
    void emitsNullForKeyWhenKeyIsNull() {
        assertSeqEquals(Arrays.asList(null, "value"), RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE),
                record(null, map(entry(RecordValue.INSTANCE, seq("value")))), 0));
    }

    @Test
    void emitsNullForValueWhenValueIsNull() {
        assertSeqEquals(Arrays.asList("key", null), RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE),
                record(map(entry(RecordKey.INSTANCE, seq("key"))), null), 0));
    }

    @Test
    void emitsNullForValuesForConcreteFields() {
        assertSeqEquals(Arrays.asList("value1", null),
                RowBuilder.buildRow(seq(new RecordValueField("field1"), new RecordValueField("field2")),
                        record(null, map(entry(new RecordValueField("field1"), seq("value1")))), 0));
    }

    @Test
    void emitsNullColumnsWhenAllFieldsFromRecordValueAreNull() {
        assertSeqEquals(Arrays.asList(null, null),
                RowBuilder.buildRow(seq(RecordValueFields.INSTANCE), record(null, null), 2));
    }

    @Test
    void combinesPresentFieldWithAllFieldReference() {
        assertSeqEquals(List.of(1, 2, 3, TIMESTAMP),
                RowBuilder.buildRow(seq(RecordValueFields.INSTANCE, TimestampField.INSTANCE),
                        record(null, map(entry(RecordValueFields.INSTANCE, seq(1, 2, 3)))), 2));
    }

    @Test
    void emitsCorrectColumnCountWhenRecordValueIsNull() {
        assertSeqEquals(Arrays.asList("ourKey", null, null, TIMESTAMP),
                RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValueFields.INSTANCE, TimestampField.INSTANCE),
                        record(map(entry(RecordKey.INSTANCE, seq("ourKey"))), null), 4));
    }

    @Test
    void worksWithTwoAllFieldsReferences() {
        assertSeqEquals(List.of("key1", TIMESTAMP, "val1", "val2"),
                RowBuilder.buildRow(seq(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE),
                        record(map(entry(RecordKeyFields.INSTANCE, seq("key1"))),
                                map(entry(RecordValueFields.INSTANCE, seq("val1", "val2")))), 4));
    }

    @Test
    void worksWithTwoAllFieldsReferencesWhenOneIsNull() {
        assertSeqEquals(Arrays.asList(null, null, TIMESTAMP, "val1", "val2"),
                RowBuilder.buildRow(seq(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE),
                        record(null, map(entry(RecordValueFields.INSTANCE, seq("val1", "val2")))), 5));
    }

    @Test
    void failsWithTwoAllFieldsReferencesAndNullValue() {
        assertThrows(KafkaConnectorException.class,
                () -> RowBuilder.buildRow(seq(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE),
                        record(null, null), 4));
    }
}
