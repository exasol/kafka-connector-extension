package com.exasol.cloudetl.kafka.deserialization;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.cloudetl.kafka.KafkaConnectorException;

@ExtendWith(MockitoExtension.class)
class RowBuilderTest {
    private static final long TIMESTAMP = 123456789L;

    @Mock
    ConsumerRecord<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> recordMock;

    private ConsumerRecord<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> createRecord(
            final Map<FieldSpecification, List<Object>> key,
            final Map<FieldSpecification, List<Object>> value) {
        lenient().when(recordMock.key()).thenReturn(key);
        lenient().when(recordMock.value()).thenReturn(value);
        lenient().when(recordMock.timestamp()).thenReturn(TIMESTAMP);
        return recordMock;
    }

    @Test
    void emitsRecordKeyAndValue() {
        assertEquals(List.of("key", "value"), RowBuilder.buildRow(List.of(RecordKey.INSTANCE, RecordValue.INSTANCE),
                createRecord(Map.of(RecordKey.INSTANCE, List.of("key")),
                        Map.of(RecordValue.INSTANCE, List.of("value"))), 0));
    }

    @Test
    void emitsRecordTimestampKeyAndValue() {
        assertEquals(List.of("key", "value", TIMESTAMP),
                RowBuilder.buildRow(List.of(RecordKey.INSTANCE, RecordValue.INSTANCE, TimestampField.INSTANCE),
                        createRecord(Map.of(RecordKey.INSTANCE, List.of("key")),
                                Map.of(RecordValue.INSTANCE, List.of("value"))), 0));
    }

    @Test
    void emitsNullForKeyWhenKeyIsNull() {
        assertEquals(Arrays.asList(null, "value"), RowBuilder.buildRow(List.of(RecordKey.INSTANCE, RecordValue.INSTANCE),
                createRecord(null, Map.of(RecordValue.INSTANCE, List.of("value"))), 0));
    }

    @Test
    void emitsNullForValueWhenValueIsNull() {
        assertEquals(Arrays.asList("key", null), RowBuilder.buildRow(List.of(RecordKey.INSTANCE, RecordValue.INSTANCE),
                createRecord(Map.of(RecordKey.INSTANCE, List.of("key")), null), 0));
    }

    @Test
    void emitsNullForValuesForConcreteFields() {
        assertEquals(Arrays.asList("value1", null),
                RowBuilder.buildRow(List.of(new RecordValueField("field1"), new RecordValueField("field2")),
                        createRecord(null, Map.of(new RecordValueField("field1"), List.of("value1"))), 0));
    }

    @Test
    void emitsNullColumnsWhenAllFieldsFromRecordValueAreNull() {
        assertEquals(Arrays.asList(null, null),
                RowBuilder.buildRow(List.of(RecordValueFields.INSTANCE), createRecord(null, null), 2));
    }

    @Test
    void combinesPresentFieldWithAllFieldReference() {
        assertEquals(List.of(1, 2, 3, TIMESTAMP),
                RowBuilder.buildRow(List.of(RecordValueFields.INSTANCE, TimestampField.INSTANCE),
                        createRecord(null, Map.of(RecordValueFields.INSTANCE, List.of(1, 2, 3))), 2));
    }

    @Test
    void emitsCorrectColumnCountWhenRecordValueIsNull() {
        assertEquals(Arrays.asList("ourKey", null, null, TIMESTAMP),
                RowBuilder.buildRow(List.of(RecordKey.INSTANCE, RecordValueFields.INSTANCE, TimestampField.INSTANCE),
                        createRecord(Map.of(RecordKey.INSTANCE, List.of("ourKey")), null), 4));
    }

    @Test
    void worksWithTwoAllFieldsReferences() {
        assertEquals(List.of("key1", TIMESTAMP, "val1", "val2"),
                RowBuilder.buildRow(List.of(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE),
                        createRecord(Map.of(RecordKeyFields.INSTANCE, List.of("key1")),
                                Map.of(RecordValueFields.INSTANCE, List.of("val1", "val2"))),
                        4));
    }

    @Test
    void worksWithTwoAllFieldsReferencesWhenOneIsNull() {
        assertEquals(Arrays.asList(null, null, TIMESTAMP, "val1", "val2"),
                RowBuilder.buildRow(List.of(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE),
                        createRecord(null, Map.of(RecordValueFields.INSTANCE, List.of("val1", "val2"))), 5));
    }

    @Test
    void failsWithTwoAllFieldsReferencesAndNullValue() {
        final List<GlobalFieldSpecification> fieldSequence = List.of(RecordKeyFields.INSTANCE, TimestampField.INSTANCE, RecordValueFields.INSTANCE);
        final ConsumerRecord<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> consumerRecord = createRecord(null, null);
        assertThrows(KafkaConnectorException.class, () -> RowBuilder.buildRow(fieldSequence, consumerRecord, 4));
    }
}
