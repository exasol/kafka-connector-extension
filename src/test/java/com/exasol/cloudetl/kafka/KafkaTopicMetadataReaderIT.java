package com.exasol.cloudetl.kafka;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.jupiter.api.Test;

import com.exasol.*;

class KafkaTopicMetadataReaderIT extends KafkaIntegrationTest {
    @Override
    Map<String, String> additionalProperties() {
        return Map.of("SCHEMA_REGISTRY_URL", SCHEMA_REGISTRY_URL);
    }

    @Test
    void emitsDefaultPartitionIdMaxOffsetPairsWithSingleTopicPartition() throws Exception {
        final var iterator = mockExasolIterator(this.properties, List.of(0), List.of(-1L));
        KafkaTopicMetadataReader.run(mock(ExaMetadata.class), iterator);
        verify(iterator).emit(Integer.valueOf(0), Long.valueOf(-1));
    }

    @Test
    void emitsDefaultPartitionIdMaxOffsetPairsWithMoreTopicPartitions() throws Exception {
        createCustomTopic(this.topic, 3);
        final var iterator = mockExasolIterator(this.properties, List.of(0), List.of(-1L));
        KafkaTopicMetadataReader.run(mock(ExaMetadata.class), iterator);

        verify(iterator, times(3)).emit(anyInt(), anyLong());
        for (int partitionId = 0; partitionId < 3; partitionId++) {
            verify(iterator).emit(Integer.valueOf(partitionId), Long.valueOf(-1));
        }
    }

    @Test
    void emitsPartitionIdMaxOffsetPairsWithAdditionalTopicPartitions() throws Exception {
        createCustomTopic(this.topic, 3);
        final var iterator = mockExasolIterator(this.properties, List.of(0, 1), List.of(3L, 4L));
        KafkaTopicMetadataReader.run(mock(ExaMetadata.class), iterator);

        verify(iterator, times(3)).emit(anyInt(), anyLong());
        verify(iterator).emit(Integer.valueOf(0), Long.valueOf(3));
        verify(iterator).emit(Integer.valueOf(1), Long.valueOf(4));
        verify(iterator).emit(Integer.valueOf(2), Long.valueOf(-1));
    }

    @Test
    void emitsPartitionIdMaxOffsetPairsWithFewerTopicPartitions() throws Exception {
        createCustomTopic(this.topic, 2);
        final var iterator = mockExasolIterator(this.properties, List.of(1, 3), List.of(7L, 17L));
        KafkaTopicMetadataReader.run(mock(ExaMetadata.class), iterator);

        verify(iterator, times(2)).emit(anyInt(), anyLong());
        verify(iterator).emit(Integer.valueOf(0), Long.valueOf(-1));
        verify(iterator).emit(Integer.valueOf(1), Long.valueOf(7));
    }

    @Test
    void throwsIfItCannotCreateKafkaConsumer() throws Exception {
        createCustomTopic(this.topic);
        final Map<String, String> newProperties = new LinkedHashMap<>(this.properties);
        newProperties.put("BOOTSTRAP_SERVERS", "kafka01.internal:9092");
        final var iterator = mockExasolIterator(newProperties, List.of(0), List.of(-1L));

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> KafkaTopicMetadataReader.run(mock(ExaMetadata.class), iterator));

        assertTrue(thrown.getMessage().contains("Could not create a Kafka consumer for topic"));
    }

    @Test
    void catchesWhenEmitThrowsExaDataTypeException() throws Exception {
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> emitThrowsAnException(ExaDataTypeException.class));
        assertTrue(thrown.getMessage().contains("Error emitting metadata information for topic"));
    }

    @Test
    void catchesWhenEmitThrowsExaIterationException() throws Exception {
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> emitThrowsAnException(ExaIterationException.class));
        assertTrue(thrown.getMessage().contains("Error iterating Exasol metadata iterator for topic"));
    }

    private <T extends Throwable> void emitThrowsAnException(final Class<T> exception) throws Exception {
        createCustomTopic(this.topic, 2);
        final var iterator = mockExasolIterator(this.properties, List.of(1, 3), List.of(7L, 17L));
        doThrow(exception).when(iterator).emit(Integer.valueOf(1), Long.valueOf(7));
        KafkaTopicMetadataReader.run(mock(ExaMetadata.class), iterator);
    }
}
