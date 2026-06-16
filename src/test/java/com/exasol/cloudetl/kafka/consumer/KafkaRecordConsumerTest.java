package com.exasol.cloudetl.kafka.consumer;

import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.Duration;
import java.util.*;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.exasol.ExaIterator;
import com.exasol.cloudetl.kafka.*;
import com.exasol.cloudetl.kafka.deserialization.*;

@SuppressWarnings("unchecked")
@ExtendWith(MockitoExtension.class)
class KafkaRecordConsumerTest {
    private static final String TOPIC_NAME = "topicName";
    private static final TopicPartition TOPIC_PARTITION = new TopicPartition(TOPIC_NAME, 0);
    private static final scala.collection.immutable.Map<String, String> DEFAULT_PROPERTIES = map(
            entry("TOPIC_NAME", TOPIC_NAME), entry("RECORD_KEY_FORMAT", "string"), entry("RECORD_VALUE_FORMAT", "string"));
    private static final scala.collection.immutable.Map<String, String> MIN_MAX_THRESHOLD_PROPERTIES = map(
            entry("MIN_RECORDS_PER_RUN", "2"), entry("MAX_RECORDS_PER_RUN", "4"));
    private static final scala.collection.immutable.Map<String, String> CONSUME_ALL_OFFSETS_PROPERTIES = map(
            entry("CONSUME_ALL_OFFSETS", "true"));
    private static final Duration DEFAULT_TIMEOUT = Duration.ofMillis(30000);
    private static final long DEFAULT_END_OFFSET = 4L;
    private static final ConsumerRecords<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> EMPTY_CONSUMER_RECORDS = new ConsumerRecords<>(
            Collections.emptyMap());

    @Mock
    ExaIterator iteratorMock;
    @Mock
    private KafkaConsumer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> consumerMock;

    @BeforeEach
    void beforeEach() {
        when(this.consumerMock.endOffsets(List.of(TOPIC_PARTITION))).thenReturn(Map.of(TOPIC_PARTITION, DEFAULT_END_OFFSET));
    }

    @Test
    void emitsAllRecordsUsingMinAndMaxRecordCounts() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(recordBatch(0, 1), recordBatch(2, 3));
        checker(MIN_MAX_THRESHOLD_PROPERTIES).assertEmitCount(4);
    }

    @Test
    void emitsAllRecordsUsingMinAndMaxRecordCountsWithEmptyRecords() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(recordBatch(0, 1), EMPTY_CONSUMER_RECORDS, recordBatch(2, 3));
        when(this.consumerMock.position(TOPIC_PARTITION)).thenReturn(2L);
        checker(MIN_MAX_THRESHOLD_PROPERTIES).assertEmitCount(2);
    }

    @Test
    void emitsAllRecordsUsingConsumeAllOffsets() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(recordBatch(0, 1), recordBatch(2, 3));
        checker(CONSUME_ALL_OFFSETS_PROPERTIES).assertEmitCount(4);
    }

    @Test
    void emitsAllRecordsWithConsumeAllOffsetsPriorityOverMinAndMaxThresholds() throws Exception {
        when(this.consumerMock.endOffsets(List.of(TOPIC_PARTITION))).thenReturn(Map.of(TOPIC_PARTITION, 8L));
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(recordBatch(0, 1), recordBatch(2, 3), recordBatch(4, 5, 6, 7));
        checker(merge(MIN_MAX_THRESHOLD_PROPERTIES, CONSUME_ALL_OFFSETS_PROPERTIES)).assertEmitCount(8);
    }

    @Test
    void emitsAllRecordsUsingConsumeAllOffsetsWithEmptyRecordsInBetween() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(recordBatch(0, 1), EMPTY_CONSUMER_RECORDS, recordBatch(2, 3));
        when(this.consumerMock.position(TOPIC_PARTITION)).thenReturn(2L);
        checker(CONSUME_ALL_OFFSETS_PROPERTIES).assertEmitCount(4);
    }

    @Test
    void returnsWithoutEmittingRecordsWhenTopicIsEmpty() throws Exception {
        when(this.consumerMock.endOffsets(List.of(TOPIC_PARTITION))).thenReturn(Map.of(TOPIC_PARTITION, 1L));
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(EMPTY_CONSUMER_RECORDS);
        when(this.consumerMock.position(TOPIC_PARTITION)).thenReturn(1L);
        checker(CONSUME_ALL_OFFSETS_PROPERTIES).assertEmitCount(0);
    }

    @Test
    void returnsWithoutEmittingRecordsWhenAlreadyCaughtUp() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(EMPTY_CONSUMER_RECORDS)
                .thenThrow(new RuntimeException("test should not poll twice"));
        when(this.consumerMock.position(TOPIC_PARTITION)).thenReturn(4L);
        checker(CONSUME_ALL_OFFSETS_PROPERTIES, DEFAULT_END_OFFSET - 1).assertEmitCount(0);
    }

    @Test
    void returnsWithEmptyRecordsAndOffsetReset() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(EMPTY_CONSUMER_RECORDS).thenReturn(EMPTY_CONSUMER_RECORDS);
        when(this.consumerMock.position(TOPIC_PARTITION)).thenReturn(4L);
        checker(CONSUME_ALL_OFFSETS_PROPERTIES, 2L).assertEmitCount(0);
    }

    @Test
    void emitsRecordsUsingConsumeAllOffsetsWithEmptyRecordsAndOffsetReset() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(EMPTY_CONSUMER_RECORDS).thenReturn(EMPTY_CONSUMER_RECORDS)
                .thenReturn(recordBatch(2, 3));
        when(this.consumerMock.position(TOPIC_PARTITION)).thenReturn(2L);
        checker(CONSUME_ALL_OFFSETS_PROPERTIES).assertEmitCount(2);
    }

    @Test
    void emitsRecordsUsingConsumeAllOffsetsWithEmptyRecordsAndOffsetResetToFirst() throws Exception {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenReturn(EMPTY_CONSUMER_RECORDS).thenReturn(EMPTY_CONSUMER_RECORDS)
                .thenReturn(recordBatch(2, 3));
        when(this.consumerMock.position(TOPIC_PARTITION)).thenReturn(1L);
        checker(CONSUME_ALL_OFFSETS_PROPERTIES).assertEmitCount(2);
    }

    @Test
    void throwsIllegalStateException() {
        assertExpectedException(new IllegalStateException(), "E-KCE-20", null);
    }

    @Test
    void throwsInvalidTopicException() {
        assertExpectedException(new InvalidTopicException(), "E-KCE-21", null);
    }

    @Test
    void throwsAuthorizationException() {
        assertExpectedException(new AuthorizationException("ErrorCause"), "E-KCE-22", "ErrorCause");
    }

    @Test
    void throwsAuthenticationException() {
        assertExpectedException(new AuthenticationException("authError"), "E-KCE-23", "authError");
    }

    private void assertExpectedException(final Exception exception, final String errorCode, final String cause) {
        when(this.consumerMock.poll(DEFAULT_TIMEOUT)).thenThrow(exception);
        final KafkaImportChecker checker = checker(CONSUME_ALL_OFFSETS_PROPERTIES);
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class, () -> checker.assertEmitCount(1));
        assertTrue(thrown.getMessage().startsWith(errorCode));
        assertThat(thrown.getMessage(), startsWith(errorCode));
        if (cause != null) {
            assertTrue(thrown.getMessage().contains(cause));
        }
    }

    private ConsumerRecords<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> recordBatch(
            final long... offsets) {
        final List<ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>>> records = new ArrayList<>();
        for (final long offset : offsets) {
            records.add(new ConsumerRecord<>(TOPIC_NAME, 0, offset,
                    map(entry(RecordKey.INSTANCE, seq("key"))), map(entry(RecordValue.INSTANCE, seq(String.valueOf(offset))))));
        }
        return new ConsumerRecords<>(Map.of(TOPIC_PARTITION, records));
    }

    private KafkaImportChecker checker(
            final scala.collection.immutable.Map<String, String> additionalProperties) {
        return checker(additionalProperties, 0L);
    }

    private KafkaImportChecker checker(final scala.collection.immutable.Map<String, String> additionalProperties,
            final long startOffset) {
        return new KafkaImportChecker(additionalProperties, startOffset);
    }

    private scala.collection.immutable.Map<String, String> merge(
            final scala.collection.immutable.Map<String, String> first,
            final scala.collection.immutable.Map<String, String> second) {
        final Map<String, String> merged = new LinkedHashMap<>(javaMap(first));
        merged.putAll(javaMap(second));
        return ScalaCollections.immutableMap(merged);
    }

    private final class KafkaImportChecker {
        private final scala.collection.immutable.Map<String, String> additionalProperties;
        private final long startOffset;

        private KafkaImportChecker(final scala.collection.immutable.Map<String, String> additionalProperties,
                final long startOffset) {
            this.additionalProperties = additionalProperties;
            this.startOffset = startOffset;
        }

        void assertEmitCount(final int count) throws Exception {
            final var properties = new KafkaConsumerProperties(merge(DEFAULT_PROPERTIES, this.additionalProperties));
            new TestKafkaRecordConsumer(properties, this.startOffset).emit(iteratorMock);
            verify(iteratorMock, times(count)).emit(any(Object[].class));
        }
    }

    private final class TestKafkaRecordConsumer extends KafkaRecordConsumer {
        private TestKafkaRecordConsumer(final KafkaConsumerProperties properties, final long startOffset) {
            super(properties, 0, startOffset, seq(String.class, Long.class, Long.class), 3, 1L, "vm1");
        }

        @Override
        protected KafkaConsumer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> getRecordConsumer() {
            return consumerMock;
        }
    }
}
