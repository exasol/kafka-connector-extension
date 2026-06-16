package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;

import com.exasol.ExaMetadata;

class KafkaTopicDataImporterJsonToColumnsIT extends KafkaIntegrationTest {
    @Override
    Map<String, String> additionalProperties() {
        return Map.of("RECORD_FORMAT", "JSON", "RECORD_FIELDS", "value.col_str,value.col_int,value.col_object");
    }

    @Test
    void deserializesJsonToExasolRow() throws Exception {
        createCustomTopic(this.topic);
        publishStringToKafka(this.topic,
                "{\"col_str\":\"val1\",\"col_int\":11,\"col_ignore\":\"not_to_include\",\"col_object\":{\"field\":\"value\"}}");
        publishStringToKafka(this.topic, "{\"col_str\":\"val2\",\"col_int\":22,\"col_ignore\":\"not_to_include\"}");

        final var iterator = mockExasolIterator(this.properties, List.of(0), List.of(-1L));
        final ExaMetadata metadata = mockMetadata(String.class, Integer.class, String.class, Integer.class, Long.class);

        KafkaTopicDataImporter.run(metadata, iterator);

        assertAll(() -> verify(iterator, times(2)).emit(any(Object[].class)),
                () -> verify(iterator).emit("val1", Integer.valueOf(11), "{\"field\":\"value\"}",
                        Integer.valueOf(0), Long.valueOf(0)),
                () -> verify(iterator).emit("val2", Integer.valueOf(22), null, Integer.valueOf(0), Long.valueOf(1)));
    }

    static ExaMetadata mockMetadata(final Class<?>... outputColumnTypes) throws Exception {
        final ExaMetadata metadata = mock(ExaMetadata.class);
        when(metadata.getOutputColumnCount()).thenReturn((long) outputColumnTypes.length);
        when(metadata.getOutputColumnType(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
            final int columnIndex = invocation.getArgument(0);
            return outputColumnTypes[columnIndex];
        });
        return metadata;
    }
}
