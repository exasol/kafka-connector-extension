package com.exasol.cloudetl.kafka;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.errors.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class KafkaTopicMetadataReaderTest {
    private static final String TOPIC = "orders";
    @Mock
    KafkaConsumer<Void, Void> consumerMock;

    @Test
    void readsTopicPartitionsAndClosesConsumer() {
        when(consumerMock.partitionsFor(TOPIC)).thenReturn(Arrays.asList(new PartitionInfo(TOPIC, 0, null, null, null),
                new PartitionInfo(TOPIC, 2, null, null, null)));

        final var partitions = KafkaTopicMetadataReader.getTopicPartitions(consumerMock, TOPIC);

        assertAll(() -> assertEquals(Arrays.asList(0, 2), partitions),
                () -> verify(consumerMock).close());
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
        when(consumerMock.partitionsFor(TOPIC)).thenThrow(new IllegalStateException("boom"));

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> KafkaTopicMetadataReader.getTopicPartitions(consumerMock, TOPIC));

        assertAll(() -> assertThat(thrown.getMessage(), containsString("F-KCE-27")),
                () -> assertInstanceOf(IllegalStateException.class, thrown.getCause()),
                () -> verify(consumerMock).close());
    }

    private void assertMappedException(final RuntimeException exception, final String errorCode,
            final String messagePart) {
        when(consumerMock.partitionsFor(TOPIC)).thenThrow(exception);

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> KafkaTopicMetadataReader.getTopicPartitions(consumerMock, TOPIC));

        assertAll(() -> assertThat(thrown.getMessage(), containsString(errorCode)),
                () -> assertThat(thrown.getMessage(), containsString(messagePart)),
                () -> verify(consumerMock).close());
    }
}
