package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;

class KafkaTopicDataImporterNullValueIT extends KafkaIntegrationTest {
    @Override
    Map<String, String> additionalProperties() {
        return Map.of("SCHEMA_REGISTRY_URL", SCHEMA_REGISTRY_URL);
    }

    @Test
    void emitFillsColumnsWithNullIfRecordValueIsNull() throws Exception {
        createCustomTopic(this.topic);
        publishToKafka(new ProducerRecord<String, String>(this.topic, null), new StringSerializer());
        publishToKafka(new ProducerRecord<String, String>(this.topic, null), new StringSerializer());

        final var iterator = mockExasolIterator(this.properties, List.of(0), List.of(-1L));

        KafkaTopicDataImporter.run(KafkaTopicDataImporterJsonToColumnsIT.mockMetadata(Object.class, Object.class, Integer.class, Long.class),
                iterator);

        assertAll(() -> verify(iterator, times(2)).emit(any(Object[].class)),
                () -> verify(iterator).emit(null, null, 0, 0L),
                () -> verify(iterator).emit(null, null, 0, 1L));
    }
}
