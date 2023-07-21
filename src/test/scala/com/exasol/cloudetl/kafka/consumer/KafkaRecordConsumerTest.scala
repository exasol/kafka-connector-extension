package com.exasol.cloudetl.kafka

import java.time.Duration
import java.util.{Map => JMap}
import java.util.Arrays
import java.util.Collections

import com.exasol.ExaIterator
import com.exasol.cloudetl.kafka.consumer.KafkaRecordConsumer
import com.exasol.cloudetl.kafka.deserialization.FieldSpecification
import com.exasol.cloudetl.kafka.deserialization.RecordKey
import com.exasol.cloudetl.kafka.deserialization.RecordValue

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.TopicPartition
import org.apache.kafka.common.errors._
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.when
import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KafkaRecordConsumerTest extends AnyFunSuite with BeforeAndAfterEach with MockitoSugar {

  private[this] val topicName = "topicName"
  private[this] val topicPartition = new TopicPartition(topicName, 0)
  private[this] val defaultProperties = Map(
    "TOPIC_NAME" -> topicName,
    "RECORD_KEY_FORMAT" -> "string",
    "RECORD_VALUE_FORMAT" -> "string"
  )
  private[this] val minMaxThresholdProperties = Map(
    "MIN_RECORDS_PER_RUN" -> "2",
    "MAX_RECORDS_PER_RUN" -> "4"
  )
  private[this] val consumeAllOffsetsProperties = Map(
    "CONSUME_ALL_OFFSETS" -> "true"
  )
  private[this] val defaultTimeout = Duration.ofMillis(30000)
  private[this] val defaultEndOffset = 4L
  private[this] val emptyConsumerRecords = new ConsumerRecords[FieldType, FieldType](Collections.emptyMap())

  type FieldType = Map[FieldSpecification, Seq[Any]]
  private[this] var iterator: ExaIterator = _
  private[this] var consumer: KafkaConsumer[FieldType, FieldType] = _

  override final def beforeEach(): Unit = {
    iterator = mock[ExaIterator]
    consumer = mock[StubConsumer]
    when(consumer.endOffsets(Arrays.asList(topicPartition)))
      .thenReturn(JMap.of(topicPartition, defaultEndOffset))
    ()
  }

  test("emits all records using min and max record counts") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), recordBatch(Seq(2, 3)))
    KafkaImportChecker(minMaxThresholdProperties).assertEmitCount(4)
  }

  test("emits all records using min and max record counts with empty records") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), emptyConsumerRecords, recordBatch(Seq(2, 3)))
    when(consumer.position(topicPartition)).thenReturn(2L)
    KafkaImportChecker(minMaxThresholdProperties).assertEmitCount(2)
  }

  test("emits all records using consume_all_offsets") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), recordBatch(Seq(2, 3)))
    KafkaImportChecker(consumeAllOffsetsProperties).assertEmitCount(4)
  }

  test("emits all records with consume_all_offsets priority over min and max thresholds") {
    when(consumer.endOffsets(Arrays.asList(topicPartition)))
      .thenReturn(JMap.of(topicPartition, 8L))
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), recordBatch(Seq(2, 3)), recordBatch(Seq(4, 5, 6, 7)))
    KafkaImportChecker(minMaxThresholdProperties ++ consumeAllOffsetsProperties)
      .assertEmitCount(8)
  }

  test("emits all records using consume_all_offsets with empty records in between") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), emptyConsumerRecords, recordBatch(Seq(2, 3)))
    when(consumer.position(topicPartition)).thenReturn(2L)
    KafkaImportChecker(consumeAllOffsetsProperties).assertEmitCount(4)
  }

  test("returns without emitting records when topic is empty") {
    when(consumer.endOffsets(Arrays.asList(topicPartition)))
      .thenReturn(JMap.of(topicPartition, 1))
    when(consumer.poll(defaultTimeout)).thenReturn(emptyConsumerRecords)
    when(consumer.position(topicPartition)).thenReturn(1L)
    KafkaImportChecker(consumeAllOffsetsProperties).assertEmitCount(0)
  }

  test("returns without emitting records when we are already caught up") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(emptyConsumerRecords)
      .thenThrow(new RuntimeException("test should not poll twice"))
    when(consumer.position(topicPartition)).thenReturn(4L)
    KafkaImportChecker(consumeAllOffsetsProperties, startOffset = defaultEndOffset - 1).assertEmitCount(0)
  }

  test("returns with empty records and offset reset") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(emptyConsumerRecords)
      .thenReturn(emptyConsumerRecords)
    when(consumer.position(topicPartition)).thenReturn(4L)
    KafkaImportChecker(consumeAllOffsetsProperties, startOffset = 2L).assertEmitCount(0)
  }

  test("emits records using consume_all_offsets with empty records and offset reset") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(emptyConsumerRecords)
      .thenReturn(emptyConsumerRecords)
      .thenReturn(recordBatch(Seq(2, 3)))
    when(consumer.position(topicPartition)).thenReturn(2L)
    KafkaImportChecker(consumeAllOffsetsProperties).assertEmitCount(2)
  }

  test("emits records using consume_all_offsets with empty records and offset reset to first") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(emptyConsumerRecords)
      .thenReturn(emptyConsumerRecords)
      .thenReturn(recordBatch(Seq(2, 3)))
    when(consumer.position(topicPartition)).thenReturn(1L)
    KafkaImportChecker(consumeAllOffsetsProperties).assertEmitCount(2)
  }

  test("throws illegal state exception") {
    assertExpectedException(new IllegalStateException(), "E-KCE-20")
  }

  test("throws invalid topic exception") {
    assertExpectedException(new InvalidTopicException(), "E-KCE-21")
  }

  test("throws authorization exception") {
    assertExpectedException(new AuthorizationException("ErrorCause"), "E-KCE-22", Option("ErrorCause"))
  }

  test("throws authentication exception") {
    assertExpectedException(new AuthenticationException("authError"), "E-KCE-23", Option("authError"))
  }

  private[this] def assertExpectedException(
    exception: Exception,
    errorCode: String,
    cause: Option[String] = None
  ): Unit = {
    when(consumer.poll(defaultTimeout)).thenThrow(exception)
    val thrown = intercept[KafkaConnectorException] {
      KafkaImportChecker(consumeAllOffsetsProperties).assertEmitCount(1)
    }
    assert(thrown.getMessage().startsWith(errorCode))
    cause.fold {} { case message =>
      thrown.getMessage().contains(message)
      ()
    }
    ()
  }

  private[this] def recordBatch(offsets: Seq[Long]): ConsumerRecords[FieldType, FieldType] = {
    val records = new java.util.ArrayList[ConsumerRecord[FieldType, FieldType]]()
    offsets.foreach { case offset =>
      records.add(
        new ConsumerRecord[FieldType, FieldType](
          topicName,
          0,
          offset,
          Map(RecordKey -> Seq("key")),
          Map(RecordValue -> Seq(s"$offset"))
        )
      )
    }
    new ConsumerRecords[FieldType, FieldType](JMap.of(topicPartition, records))
  }

  // It is alright to use default arguments in tests.
  case class KafkaImportChecker(
    additionalProperties: Map[String, String] = Map.empty[String, String],
    startOffset: Long = 0L
  ) {
    final def assertEmitCount(count: Int): Unit = {
      val properties = new KafkaConsumerProperties(defaultProperties ++ additionalProperties)
      TestKafkaRecordConsumer(properties, startOffset).emit(iterator)
      verify(iterator, times(count)).emit(any(classOf[Array[Object]]))
    }
  }

  case class TestKafkaRecordConsumer(properties: KafkaConsumerProperties, startOffset: Long)
      extends KafkaRecordConsumer(
        properties,
        0,
        startOffset,
        Seq(classOf[String], classOf[Long], classOf[Long]),
        3,
        1L,
        "vm1"
      ) {
    override final def getRecordConsumer(): KafkaConsumer[FieldType, FieldType] = consumer
  }

  class StubConsumer(p: java.util.Properties) extends KafkaConsumer[FieldType, FieldType](p) {
    //
  }

}
