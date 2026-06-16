package com.exasol.cloudetl.kafka;

import static com.exasol.cloudetl.kafka.TestCollections.assertSeqEquals;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.errors.*;
import org.junit.jupiter.api.Test;

class KafkaTopicMetadataReaderTest {
    private static final String TOPIC = "orders";

    @Test
    void readsTopicPartitionsAndClosesConsumer() {
        final KafkaConsumer<Void, Void> consumer = consumer();
        when(consumer.partitionsFor(TOPIC)).thenReturn(Arrays.asList(new PartitionInfo(TOPIC, 0, null, null, null),
                new PartitionInfo(TOPIC, 2, null, null, null)));

        final var partitions = KafkaTopicMetadataReader.getTopicPartitions(consumer, TOPIC);

        assertSeqEquals(Arrays.asList(0, 2), partitions);
        verify(consumer).close();
    }

    @Test
    void mapsTimeoutErrorsToConnectorException() {
        assertMappedException(new TimeoutException("timed out"), "E-KCE-24",
                "Timeout trying to connect to Kafka brokers");
    }

    @Test
    void mapsAuthorizationErrorsToConnectorException() {
        assertMappedException(new AuthorizationException("missing ACL"), "E-KCE-25",
                "does not have access to read the given topic");
    }

    @Test
    void mapsAuthenticationErrorsToConnectorException() {
        assertMappedException(new AuthenticationException("bad credentials"), "E-KCE-26",
                "Failed to authenticate to the Kafka cluster");
    }

    @Test
    void mapsUnexpectedErrorsToConnectorException() {
        final KafkaConsumer<Void, Void> consumer = consumer();
        when(consumer.partitionsFor(TOPIC)).thenThrow(new IllegalStateException("boom"));

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> KafkaTopicMetadataReader.getTopicPartitions(consumer, TOPIC));

        assertAll(() -> assertTrue(thrown.getMessage().contains("F-KCE-27")),
                () -> assertInstanceOf(IllegalStateException.class, thrown.getCause()));
        verify(consumer).close();
    }

    private void assertMappedException(final RuntimeException exception, final String errorCode,
            final String messagePart) {
        final KafkaConsumer<Void, Void> consumer = consumer();
        when(consumer.partitionsFor(TOPIC)).thenThrow(exception);

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> KafkaTopicMetadataReader.getTopicPartitions(consumer, TOPIC));

        assertAll(() -> assertTrue(thrown.getMessage().contains(errorCode)),
                () -> assertTrue(thrown.getMessage().contains(messagePart)));
        verify(consumer).close();
    }

    @SuppressWarnings("unchecked")
    private KafkaConsumer<Void, Void> consumer() {
        return mock(KafkaConsumer.class);
    }
}
