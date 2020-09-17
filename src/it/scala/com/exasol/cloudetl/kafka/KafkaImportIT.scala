package com.exasol.cloudetl.scriptclasses

import java.lang.{Integer => JInt}
import java.lang.{Long => JLong}

import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.KafkaIntegrationTest

import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class KafkaImportIT extends KafkaIntegrationTest {

  test("run emits records from starting initial offset") {
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("abc", 3, 13))
    publishToKafka(topic, AvroRecord("hello", 4, 14))

    val iter = mockExasolIterator(properties, Seq(0), Seq(-1))
    KafkaImport.run(mock[ExaMetadata], iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(2)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
    verify(iter, times(1)).emit(
      "abc",
      JInt.valueOf(3),
      JLong.valueOf(13),
      JInt.valueOf(0),
      JLong.valueOf(0)
    )
    verify(iter, times(1)).emit(
      "hello",
      JInt.valueOf(4),
      JLong.valueOf(14),
      JInt.valueOf(0),
      JLong.valueOf(1)
    )
  }

  test("run emits records starting from provided offset") {
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("abc", 3, 13))
    publishToKafka(topic, AvroRecord("hello", 4, 14))
    publishToKafka(topic, AvroRecord("def", 7, 17))
    publishToKafka(topic, AvroRecord("xyz", 13, 23))

    // records at 0, 1 are already read, committed
    val iter = mockExasolIterator(properties, Seq(0), Seq(1))
    KafkaImport.run(mock[ExaMetadata], iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(2)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
    verify(iter, times(1)).emit(
      "def",
      JInt.valueOf(7),
      JLong.valueOf(17),
      JInt.valueOf(0),
      JLong.valueOf(2)
    )
    verify(iter, times(1)).emit(
      "xyz",
      JInt.valueOf(13),
      JLong.valueOf(23),
      JInt.valueOf(0),
      JLong.valueOf(3)
    )
  }

  test("run emits records within min / max records per run") {
    val newProperties = properties ++ Map(
      "MAX_POLL_RECORDS" -> "2",
      "MIN_RECORDS_PER_RUN" -> "2",
      "MAX_RECORDS_PER_RUN" -> "4"
    )
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("abc", 3, 13))
    publishToKafka(topic, AvroRecord("hello", 4, 14))
    publishToKafka(topic, AvroRecord("def", 7, 17))
    publishToKafka(topic, AvroRecord("xyz", 13, 23))

    // comsumer in two batches each with 2 records
    val iter = mockExasolIterator(newProperties, Seq(0), Seq(-1))
    KafkaImport.run(mock[ExaMetadata], iter)

    verify(iter, times(4)).emit(Seq(any[Object]): _*)
    verify(iter, times(4)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong],
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
  }

}
