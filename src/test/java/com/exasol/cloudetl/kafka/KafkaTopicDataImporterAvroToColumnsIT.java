package com.exasol.cloudetl.kafka;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.*;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Test;

import com.exasol.*;

class KafkaTopicDataImporterAvroToColumnsIT extends KafkaTopicDataImporterAvroIT {
    @Test
    void emitsRecordsFromStartingInitialOffset() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, new AvroRecord("abc", 3, 13));
        publishAvro(this.topic, new AvroRecord("hello", 4, 14));

        final var iterator = runImporter(this.properties, List.of(-1L));

        assertAll(() -> verify(iterator, times(2)).emit(any(Object[].class)),
                () -> verify(iterator).emit("abc", 3, 13L, 0, 0L),
                () -> verify(iterator).emit("hello", 4, 14L, 0, 1L));
    }

    @Test
    void emitsRecordsWhenStartingOffsetOfTopicIsGreaterZero() throws Exception {
        createCustomTopic(this.topic);
        final int startingOffset = 12;
        for (int recordNr = 0; recordNr < startingOffset; recordNr++) {
            publishAvro(this.topic, new AvroRecord("Some record that we delete to ensure the offset does not start at zero",
                    recordNr, 13));
        }
        deleteRecordsFromTopic(this.topic, startingOffset);
        publishAvro(this.topic, new AvroRecord("abc", 3, 13));
        publishAvro(this.topic, new AvroRecord("hello", 4, 14));

        final var iterator = runImporter(this.properties, List.of(-1L));

        assertAll(() -> verify(iterator, times(2)).emit(any(Object[].class)),
                () -> verify(iterator).emit("abc", 3, 13L, 0, (long) startingOffset),
                () -> verify(iterator).emit("hello", 4, 14L, 0, startingOffset + 1L));
    }

    @Test
    void emitsRecordsStartingFromProvidedOffset() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, new AvroRecord("abc", 3, 13));
        publishAvro(this.topic, new AvroRecord("hello", 4, 14));
        publishAvro(this.topic, new AvroRecord("def", 7, 17));
        publishAvro(this.topic, new AvroRecord("xyz", 13, 23));

        final var iterator = runImporter(this.properties, List.of(1L));

        assertAll(() -> verify(iterator, times(2)).emit(any(Object[].class)),
                () -> verify(iterator).emit("def", 7, 17L, 0, 2L),
                () -> verify(iterator).emit("xyz", 13, 23L, 0, 3L));
    }

    @Test
    void emitsRecordsWithinMinMaxRecordsPerRun() throws Exception {
        final Map<String, String> newProperties = new LinkedHashMap<>(this.properties);
        newProperties.put("MAX_POLL_RECORDS", "2");
        newProperties.put("MIN_RECORDS_PER_RUN", "2");
        newProperties.put("MAX_RECORDS_PER_RUN", "4");
        createCustomTopic(this.topic);
        for (final AvroRecord avroRecord : List.of(new AvroRecord("abc", 3, 13), new AvroRecord("hello", 4, 14),
                new AvroRecord("def", 7, 17), new AvroRecord("xyz", 13, 23), new AvroRecord("last", 11, 22))) {
            publishAvro(this.topic, avroRecord);
        }

        final var iterator = runImporter(newProperties, List.of(-1L));

        verify(iterator, times(4)).emit(any(Object[].class));
    }

    @Test
    void emitsRecordsUntilEndOfPartitionOffset() throws Exception {
        final Map<String, String> newProperties = new LinkedHashMap<>(this.properties);
        newProperties.put("MAX_POLL_RECORDS", "2");
        newProperties.put("MIN_RECORDS_PER_RUN", "2");
        newProperties.put("MAX_RECORDS_PER_RUN", "4");
        newProperties.put("CONSUME_ALL_OFFSETS", "true");
        createCustomTopic(this.topic);
        for (int index = 1; index <= 5; index++) {
            publishAvro(this.topic, new AvroRecord(String.valueOf(index), index, index));
        }

        final var iterator = runImporter(newProperties, List.of(-1L));

        verify(iterator, times(5)).emit(any(Object[].class));
    }

    @Test
    void catchesWhenEmitThrowsExaDataTypeException() throws Exception {
        createCustomTopic(this.topic);
        publishAvro(this.topic, new AvroRecord("first", 1, 2));
        publishAvro(this.topic, new AvroRecord("second", 3, 4));
        final var iterator = mockExasolIterator(this.properties, List.of(0), List.of(-1L));
        doThrow(ExaDataTypeException.class).when(iterator).emit("second", 3, 4L, 0, 1L);
        final ExaMetadata metadata = metadata();

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> KafkaTopicDataImporter.run(metadata, iterator));

        assertAll(() -> assertThat(thrown.getMessage(),
                containsString("Error polling for Kafka topic '" + this.topic + "' data. ")),
                () -> assertThat(thrown.getMessage(),
                        containsString("It occurs for partition '0' in node '0' and vm")));
    }

    private ExaIterator runImporter(final Map<String, String> properties, final List<Long> offsets) throws Exception {
        final var iterator = mockExasolIterator(properties, List.of(0), offsets);
        KafkaTopicDataImporter.run(metadata(), iterator);
        return iterator;
    }

    private ExaMetadata metadata() throws Exception {
        return KafkaTopicDataImporterJsonToColumnsIT.mockMetadata(String.class, Integer.class, Long.class, Integer.class,
                Long.class);
    }

    private void deleteRecordsFromTopic(final String topic, final int beforeOffset) {
        try (Admin admin = Admin.create(Map.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, BOOTSTRAP_SERVERS))) {
            final var partitions = admin.describeTopics(Collections.singletonList(topic)).allTopicNames().get().get(topic)
                    .partitions();
            final Map<TopicPartition, RecordsToDelete> recordsToDelete = new LinkedHashMap<>();
            partitions.forEach(partition -> recordsToDelete.put(new TopicPartition(topic, partition.partition()),
                    RecordsToDelete.beforeOffset(beforeOffset)));
            admin.deleteRecords(recordsToDelete).all().get();
        } catch (final Exception exception) {
            throw new IllegalStateException("Failed to delete topic records", exception);
        }
    }
}
