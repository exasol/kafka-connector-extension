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
    "MIN_RECORDS_PER_RUN" -> "2",
    "MAX_RECORDS_PER_RUN" -> "4",
    "RECORD_KEY_FORMAT" -> "string",
    "RECORD_VALUE_FORMAT" -> "string"
  )
  private[this] val defaultTimeout = Duration.ofMillis(30000)
  private[this] val defaultEndOffset = 4L
  private[this] val emptyConsumerRecords =
    new ConsumerRecords[FieldType, FieldType](Collections.emptyMap())

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
    KafkaImportChecker().assertEmitCount(4)
  }

  test("emits all records using min and max record counts with empty records") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), emptyConsumerRecords, recordBatch(Seq(2, 3)))
    KafkaImportChecker().assertEmitCount(2)
  }

  test("emits all records using consume_all_offsets") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), recordBatch(Seq(2, 3)))
    KafkaImportChecker(Map("CONSUME_ALL_OFFSETS" -> "true")).assertEmitCount(4)
  }

  test("emits all records using consume_all_offsets with empty records in between") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), emptyConsumerRecords, recordBatch(Seq(2, 3)))
    KafkaImportChecker(Map("CONSUME_ALL_OFFSETS" -> "true")).assertEmitCount(2)
  }

  test("emits all records using consume_all_offsets with empty records at the end") {
    when(consumer.poll(defaultTimeout))
      .thenReturn(recordBatch(Seq(0, 1)), emptyConsumerRecords)
    KafkaImportChecker(Map("CONSUME_ALL_OFFSETS" -> "true")).assertEmitCount(2)
  }

  private[this] def recordBatch(offsets: Seq[Long]): ConsumerRecords[FieldType, FieldType] = {
    val records = new java.util.ArrayList[ConsumerRecord[FieldType, FieldType]]()
    offsets.foreach {
      case offset =>
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
  @SuppressWarnings(Array("org.wartremover.warts.DefaultArguments"))
  case class KafkaImportChecker(
    additionalProperties: Map[String, String] = Map.empty[String, String]
  ) {
    final def assertEmitCount(count: Int): Unit = {
      val properties = new KafkaConsumerProperties(defaultProperties ++ additionalProperties)
      KafkaRecordConsumer(properties, 0, 0, 3, 1L, "vm1", consumer).emit(iterator)
      verify(iterator, times(count)).emit(Seq(any[Object]): _*)
    }
  }

  class StubConsumer(p: java.util.Properties) extends KafkaConsumer[FieldType, FieldType](p) {
    //
  }

}