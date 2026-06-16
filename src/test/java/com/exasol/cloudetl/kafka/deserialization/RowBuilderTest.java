package com.exasol.cloudetl.kafka.deserialization;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.lenient;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.cloudetl.kafka.KafkaConnectorException;

import scala.collection.immutable.Map;
import scala.collection.immutable.Seq;

@ExtendWith(MockitoExtension.class)
class RowBuilderTest {
    private static final long TIMESTAMP = 123456789L;

    @Mock
    ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> recordMock;

    private ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> createRecord(
            final scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> key,
            final scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> value) {
        lenient().when(recordMock.key()).thenReturn(key);
        lenient().when(recordMock.value()).thenReturn(value);
        lenient().when(recordMock.timestamp()).thenReturn(TIMESTAMP);
        return recordMock;
    }

    @Test
    void emitsRecordKeyAndValue() {
        assertSeqEquals(List.of("key", "value"), RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE),
                createRecord(map(entry(RecordKey.INSTANCE, seq("key"))), map(entry(RecordValue.INSTANCE, seq("value")))), 0));
    }

    @Test
    void emitsRecordTimestampKeyAndValue() {
        assertSeqEquals(List.of("key", "value", TIMESTAMP),
                RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE, TimestampField.INSTANCE),
                        createRecord(map(entry(RecordKey.INSTANCE, seq("key"))), map(entry(RecordValue.INSTANCE, seq("value")))), 0));
    }

    @Test
    void emitsNullForKeyWhenKeyIsNull() {
        assertSeqEquals(Arrays.asList(null, "value"), RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE),
                createRecord(null, map(entry(RecordValue.INSTANCE, seq("value")))), 0));
    }

    @Test
    void emitsNullForValueWhenValueIsNull() {
        assertSeqEquals(Arrays.asList("key", null), RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValue.INSTANCE),
                createRecord(map(entry(RecordKey.INSTANCE, seq("key"))), null), 0));
    }

    @Test
    void emitsNullForValuesForConcreteFields() {
        assertSeqEquals(Arrays.asList("value1", null),
                RowBuilder.buildRow(seq(new RecordValueField("field1"), new RecordValueField("field2")),
                        createRecord(null, map(entry(new RecordValueField("field1"), seq("value1")))), 0));
    }

    @Test
    void emitsNullColumnsWhenAllFieldsFromRecordValueAreNull() {
        assertSeqEquals(Arrays.asList(null, null),
                RowBuilder.buildRow(seq(RecordValueFields.INSTANCE), createRecord(null, null), 2));
    }

    @Test
    void combinesPresentFieldWithAllFieldReference() {
        assertSeqEquals(List.of(1, 2, 3, TIMESTAMP),
                RowBuilder.buildRow(seq(RecordValueFields.INSTANCE, TimestampField.INSTANCE),
                        createRecord(null, map(entry(RecordValueFields.INSTANCE, seq(1, 2, 3)))), 2));
    }

    @Test
    void emitsCorrectColumnCountWhenRecordValueIsNull() {
        assertSeqEquals(Arrays.asList("ourKey", null, null, TIMESTAMP),
                RowBuilder.buildRow(seq(RecordKey.INSTANCE, RecordValueFields.INSTANCE, TimestampField.INSTANCE),
                        createRecord(map(entry(RecordKey.INSTANCE, seq("ourKey"))), null), 4));
    }

    @Test
    void worksWithTwoAllFieldsReferences() {
        assertSeqEquals(List.of("key1", TIMESTAMP, "val1", "val2"),
                RowBuilder.buildRow(seq(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE),
                        createRecord(map(entry(RecordKeyFields.INSTANCE, seq("key1"))),
                                map(entry(RecordValueFields.INSTANCE, seq("val1", "val2")))),
                        4));
    }

    @Test
    void worksWithTwoAllFieldsReferencesWhenOneIsNull() {
        assertSeqEquals(Arrays.asList(null, null, TIMESTAMP, "val1", "val2"),
                RowBuilder.buildRow(seq(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE),
                        createRecord(null, map(entry(RecordValueFields.INSTANCE, seq("val1", "val2")))), 5));
    }

    @Test
    void failsWithTwoAllFieldsReferencesAndNullValue() {
        final Seq<GlobalFieldSpecification> fieldSequence = seq(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE);
        final ConsumerRecord<Map<FieldSpecification, Seq<Object>>, Map<FieldSpecification, Seq<Object>>> consumerRecord = createRecord(null, null);
        assertThrows(KafkaConnectorException.class, () -> RowBuilder.buildRow(fieldSequence, consumerRecord, 4));
    }
}
