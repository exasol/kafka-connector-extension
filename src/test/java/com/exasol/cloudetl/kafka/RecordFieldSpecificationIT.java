package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

import java.util.*;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import com.exasol.ExaMetadata;

class RecordFieldSpecificationIT extends KafkaTopicDataImporterAvroIT {
    private final AvroRecord customRecord = new AvroRecord("abc", 3, 13);

    @Test
    void defaultIsAllFieldsFromRecordValue() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, this.customRecord);
        assertEquals(List.of("abc", 3, 13L), getEmittedValues("value.*", List.of(String.class, Integer.class, Long.class)));
    }

    @Test
    void emitsMultipleRecordValueFieldsInSpecifiedOrder() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, this.customRecord);
        assertEquals(List.of(13L, "abc"), getEmittedValues("value.col_long, value.col_str",
                List.of(Long.class, String.class)));
    }

    @Test
    void referencesFullValue() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, this.customRecord);
        final List<Object> result = getEmittedValues("value", List.of(String.class));
        assertEquals(1, result.size());
        assertJson((String) result.get(0), "{\"col_str\":\"abc\",\"col_int\":3,\"col_long\":13}");
    }

    @Test
    void referencesKeyValuesWithDefaultRecordKeyFormatString() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, "string_key", this.customRecord);
        assertEquals(List.of("string_key", 13L),
                getEmittedValues("key, value.col_long", List.of(String.class, Long.class)));
    }

    @Test
    void failsWhenKeyIsAccessedWithConcreteField() {
        createCustomTopic(this.topic);
        publishAvro(this.topic, "string_key", this.customRecord);
        final List<Class<?>> outputColumnTypes = List.of(String.class);
        assertThrows(KafkaConnectorException.class, () -> getEmittedValues("key.someFieldReference", outputColumnTypes));
    }

    @Test
    void failsWhenKeyIsAccessedWithAllFieldsReference() {
        createCustomTopic(this.topic);
        publishAvro(this.topic, "string_key", this.customRecord);
        assertThrows(KafkaConnectorException.class, () -> getEmittedValues("key.*", List.of()));
    }

    @Test
    void handlesNullKeyAndValues() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, null, null);
        assertEquals(Arrays.asList(null, null), getEmittedValues("key, value", List.of(String.class, Object.class)));
    }

    @Test
    void handlesNullValuesCombinedWithPresentKeyAndTimestamps() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, "theKey", null);
        final List<Object> values = getEmittedValues("key, timestamp, value",
                List.of(String.class, Long.class, Object.class));
        assertAll(() -> assertEquals(3, values.size()),
                () -> assertEquals("theKey", values.get(0)),
                () -> assertInstanceOf(Long.class, values.get(1)),
                () -> assertNull(values.get(2)));
    }

    @Test
    void includesRecordTimestampWhenInFieldList() throws Exception {
        createCustomTopic(this.topic);
        final long recordTimestamp = 123456L;
        publishToKafka(new ProducerRecord<>(this.topic, 0, recordTimestamp, "record_key", this.customRecord),
                avroSerializer());
        assertEquals(Arrays.asList(recordTimestamp, null, "record_key"),
                getEmittedValues("timestamp, value.str_col, key", List.of(Long.class, Object.class, String.class)));
    }

    private void assertJson(final String actual, final String expected) {
        assertEquals(JsonArgumentMatcher.readJson(expected), JsonArgumentMatcher.readJson(actual));
    }

    private List<Object> getEmittedValues(final String recordFieldsStmt, final List<Class<?>> outputColumnTypes)
            throws Exception {
        final Map<String, String> newProperties = new LinkedHashMap<>(this.properties);
        newProperties.put("RECORD_FIELDS", recordFieldsStmt);
        final var iterator = mockExasolIterator(newProperties, List.of(0), List.of(-1L));
        final List<Class<?>> outputColumnTypesWithMeta = new ArrayList<>(outputColumnTypes);
        outputColumnTypesWithMeta.add(Integer.class);
        outputColumnTypesWithMeta.add(Long.class);
        final ExaMetadata metadata = mock(ExaMetadata.class);
        when(metadata.getOutputColumnCount()).thenReturn((long) outputColumnTypesWithMeta.size());
        when(metadata.getOutputColumnType(anyInt())).thenAnswer(invocation -> outputColumnTypesWithMeta.get(invocation.getArgument(0)));
        KafkaTopicDataImporter.run(metadata, iterator);

        final ArgumentCaptor<Object[]> captor = ArgumentCaptor.forClass(Object[].class);
        verify(iterator).emit(captor.capture());
        final Object[] values = captor.getValue();
        return Arrays.asList(Arrays.copyOf(values, values.length - 2));
    }
}
