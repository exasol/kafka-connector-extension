package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt}
import java.lang.{Long => JLong}

import com.exasol.ExaMetadata

import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

class KafkaTopicDataImporterAsJsonIT extends KafkaIntegrationTest {

  test("run emits records from starting initial offset") {
    val newProperties = properties ++ Map(
      "AS_JSON_DOC" -> "true"
    )
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("{'Value':'abc'}", 3, 13))
    publishToKafka(topic, AvroRecord("{'Value':'hello'}", 4, 14))
    publishToKafka(topic, AvroRecord("{'Value':'xyz'}", 5, 15))

    val iter = mockExasolIterator(newProperties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(3)).emit(Seq(any[Object]): _*)
    verify(iter, times(3)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
    verify(iter, times(1)).emit(
      "{\"col_str\": \"{'Value':'abc'}\", \"col_int\": 3, \"col_long\": 13}",
      JInt.valueOf(0),
      JLong.valueOf(0)
    )
    verify(iter, times(1)).emit(
      "{\"col_str\": \"{'Value':'hello'}\", \"col_int\": 4, \"col_long\": 14}",
      JInt.valueOf(0),
      JLong.valueOf(1)
    )
    verify(iter, times(1)).emit(
      "{\"col_str\": \"{'Value':'xyz'}\", \"col_int\": 5, \"col_long\": 15}",
      JInt.valueOf(0),
      JLong.valueOf(2)
    )
  }

  test("run emits records starting from provided offset") {
    val newProperties = properties ++ Map(
      "AS_JSON_DOC" -> "true"
    )
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("{'Value':'abc'}", 3, 13))
    publishToKafka(topic, AvroRecord("{'Value':'hello'}", 4, 14))
    publishToKafka(topic, AvroRecord("{'Value':'def'}", 7, 17))
    publishToKafka(topic, AvroRecord("{'Value':'xyz'}", 13, 23))

    // records at 0, 1 are already read, committed
    val iter = mockExasolIterator(newProperties, Seq(0), Seq(1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(2)).emit(Seq(any[Object]): _*)
    verify(iter, times(2)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
    verify(iter, times(1)).emit(
      "{\"col_str\": \"{'Value':'def'}\", \"col_int\": 7, \"col_long\": 17}",
      JInt.valueOf(0),
      JLong.valueOf(2)
    )
    verify(iter, times(1)).emit(
      "{\"col_str\": \"{'Value':'xyz'}\", \"col_int\": 13, \"col_long\": 23}",
      JInt.valueOf(0),
      JLong.valueOf(3)
    )
  }

  test("run emits records within min / max records per run") {
    val newProperties = properties ++ Map(
      "MAX_POLL_RECORDS" -> "2",
      "MIN_RECORDS_PER_RUN" -> "2",
      "MAX_RECORDS_PER_RUN" -> "4",
      "AS_JSON_DOC" -> "true"
    )
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("{'Value':'abc'}", 3, 13))
    publishToKafka(topic, AvroRecord("{'Value':'hello'}", 4, 14))
    publishToKafka(topic, AvroRecord("{'Value':'def'}", 7, 17))
    publishToKafka(topic, AvroRecord("{'Value':'xyz'}", 13, 23))

    // comsumer in two batches each with 2 records
    val iter = mockExasolIterator(newProperties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    verify(iter, times(4)).emit(Seq(any[Object]): _*)
    verify(iter, times(4)).emit(
      anyString(),
      anyInt().asInstanceOf[JInt],
      anyLong().asInstanceOf[JLong]
    )
  }
}
