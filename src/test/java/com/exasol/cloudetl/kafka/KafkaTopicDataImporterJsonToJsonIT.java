package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;

class KafkaTopicDataImporterJsonToJsonIT extends KafkaIntegrationTest {
    @Override
    Map<String, String> additionalProperties() {
        return Map.of("RECORD_FORMAT", "json", "AS_JSON_DOC", "true");
    }

    @Test
    void deserializesJsonToExasolRowAsFullRecord() throws Exception {
        createCustomTopic(this.topic);
        final String inputRecord1 = "{\"col_str\":\"val1\",\"col_int\":11,\"col_object\":{\"field\":\"value\"}}";
        final String inputRecord2 = "{\"col_str\":\"val2\",\"col_int\":22,\"col_object\":{\"field\":\"value\"}}";
        publishStringToKafka(this.topic, inputRecord1);
        publishStringToKafka(this.topic, inputRecord2);

        final var iterator = mockExasolIterator(this.properties, List.of(0), List.of(-1L));

        KafkaTopicDataImporter.run(KafkaTopicDataImporterJsonToColumnsIT.mockMetadata(String.class, Integer.class, Long.class),
                iterator);

        assertAll(() -> verify(iterator, times(2)).emit(any(Object[].class)),
                () -> verify(iterator).emit(jsonMatcher(inputRecord1), ArgumentMatchers.eq(Integer.valueOf(0)),
                        ArgumentMatchers.eq(Long.valueOf(0))),
                () -> verify(iterator).emit(jsonMatcher(inputRecord2), ArgumentMatchers.eq(Integer.valueOf(0)),
                        ArgumentMatchers.eq(Long.valueOf(1))));
    }
}
