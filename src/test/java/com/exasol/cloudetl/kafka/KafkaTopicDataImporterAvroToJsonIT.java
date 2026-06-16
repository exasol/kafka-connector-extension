package com.exasol.cloudetl.kafka;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

class KafkaTopicDataImporterAvroToJsonIT extends KafkaTopicDataImporterAvroIT {
    @Test
    void emitsRecordsFromStartingInitialOffset() throws Exception {
        final Map<String, String> newProperties = withProperty("AS_JSON_DOC", "true");
        createCustomTopic(this.topic);
        publishAvro(this.topic, new AvroRecord("{'Value':'abc'}", 3, 13));
        publishAvro(this.topic, new AvroRecord("{'Value':'hello'}", 4, 14));
        publishAvro(this.topic, new AvroRecord("{'Value':'xyz'}", 5, 15));

        final var iterator = mockExasolIterator(newProperties, List.of(0), List.of(-1L));
        KafkaTopicDataImporter.run(metadata(), iterator);

        verify(iterator, times(3)).emit(any(Object[].class));
        verify(iterator, times(3)).emit(anyString(), anyInt(), anyLong());
        verifyJson(iterator, "{'Value':'abc'}", 3, 13, 0);
        verifyJson(iterator, "{'Value':'hello'}", 4, 14, 1);
        verifyJson(iterator, "{'Value':'xyz'}", 5, 15, 2);
    }

    @Test
    void emitsRecordsStartingFromProvidedOffset() throws Exception {
        final Map<String, String> newProperties = withProperty("AS_JSON_DOC", "true");
        createCustomTopic(this.topic);
        publishAvro(this.topic, new AvroRecord("{'Value':'abc'}", 3, 13));
        publishAvro(this.topic, new AvroRecord("{'Value':'hello'}", 4, 14));
        publishAvro(this.topic, new AvroRecord("{'Value':'def'}", 7, 17));
        publishAvro(this.topic, new AvroRecord("{'Value':'xyz'}", 13, 23));

        final var iterator = mockExasolIterator(newProperties, List.of(0), List.of(1L));
        KafkaTopicDataImporter.run(metadata(), iterator);

        verify(iterator, times(2)).emit(any(Object[].class));
        verify(iterator, times(2)).emit(anyString(), anyInt(), anyLong());
        verifyJson(iterator, "{'Value':'def'}", 7, 17, 2);
        verifyJson(iterator, "{'Value':'xyz'}", 13, 23, 3);
    }

    @Test
    void emitsRecordsWithinMinMaxRecordsPerRun() throws Exception {
        final Map<String, String> newProperties = new LinkedHashMap<>(this.properties);
        newProperties.put("MAX_POLL_RECORDS", "2");
        newProperties.put("MIN_RECORDS_PER_RUN", "2");
        newProperties.put("MAX_RECORDS_PER_RUN", "4");
        newProperties.put("AS_JSON_DOC", "true");
        createCustomTopic(this.topic);
        publishAvro(this.topic, new AvroRecord("{'Value':'abc'}", 3, 13));
        publishAvro(this.topic, new AvroRecord("{'Value':'hello'}", 4, 14));
        publishAvro(this.topic, new AvroRecord("{'Value':'def'}", 7, 17));
        publishAvro(this.topic, new AvroRecord("{'Value':'xyz'}", 13, 23));

        final var iterator = mockExasolIterator(newProperties, List.of(0), List.of(-1L));
        KafkaTopicDataImporter.run(metadata(), iterator);

        verify(iterator, times(4)).emit(any(Object[].class));
        verify(iterator, times(4)).emit(anyString(), anyInt(), anyLong());
    }

    private Map<String, String> withProperty(final String key, final String value) {
        final Map<String, String> result = new LinkedHashMap<>(this.properties);
        result.put(key, value);
        return result;
    }

    private com.exasol.ExaMetadata metadata() throws Exception {
        return KafkaTopicDataImporterJsonToColumnsIT.mockMetadata(String.class, Integer.class, Long.class);
    }

    private void verifyJson(final com.exasol.ExaIterator iterator, final String colStr, final int colInt,
            final long colLong, final long offset) throws Exception {
        verify(iterator).emit(jsonMatcher("{\"col_str\":\"" + colStr + "\",\"col_int\":" + colInt
                + ",\"col_long\":" + colLong + "}"), ArgumentMatchers.eq(Integer.valueOf(0)),
                ArgumentMatchers.eq(Long.valueOf(offset)));
    }
}
