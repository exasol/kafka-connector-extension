package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt}
import java.lang.{Long => JLong}

import com.exasol.ExaMetadata

import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers._
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.when
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

class KafkaTopicDataImporterAvroToJsonIT extends KafkaTopicDataImporterAvroIT {

  test("run emits records from starting initial offset") {
    val newProperties = properties ++ Map("AS_JSON_DOC" -> "true")
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("{'Value':'abc'}", 3, 13))
    publishToKafka(topic, AvroRecord("{'Value':'hello'}", 4, 14))
    publishToKafka(topic, AvroRecord("{'Value':'xyz'}", 5, 15))

    val mockedIterator = mockExasolIterator(newProperties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(getMockedMetadata(), mockedIterator)

    verify(mockedIterator, times(3)).emit(any(classOf[Array[Object]]))
    verify(mockedIterator, times(3)).emit(anyString(), anyInt().asInstanceOf[JInt], anyLong().asInstanceOf[JLong])

    verify(mockedIterator, times(1)).emit(
      jsonMatcher("{\"col_str\": \"{'Value':'abc'}\", \"col_int\": 3, \"col_long\": 13}"),
      ArgumentMatchers.eq(JInt.valueOf(0)),
      ArgumentMatchers.eq(JLong.valueOf(0))
    )
    verify(mockedIterator, times(1)).emit(
      jsonMatcher("{\"col_str\": \"{'Value':'hello'}\", \"col_int\": 4, \"col_long\": 14}"),
      ArgumentMatchers.eq(JInt.valueOf(0)),
      ArgumentMatchers.eq(JLong.valueOf(1))
    )
    verify(mockedIterator, times(1)).emit(
      jsonMatcher("{\"col_str\": \"{'Value':'xyz'}\", \"col_int\": 5, \"col_long\": 15}"),
      ArgumentMatchers.eq(JInt.valueOf(0)),
      ArgumentMatchers.eq(JLong.valueOf(2))
    )
  }

  test("run emits records starting from provided offset") {
    val newProperties = properties ++ Map("AS_JSON_DOC" -> "true")
    createCustomTopic(topic)
    publishToKafka(topic, AvroRecord("{'Value':'abc'}", 3, 13))
    publishToKafka(topic, AvroRecord("{'Value':'hello'}", 4, 14))
    publishToKafka(topic, AvroRecord("{'Value':'def'}", 7, 17))
    publishToKafka(topic, AvroRecord("{'Value':'xyz'}", 13, 23))

    // records at 0, 1 are already read, committed
    val mockedIterator = mockExasolIterator(newProperties, Seq(0), Seq(1))
    KafkaTopicDataImporter.run(getMockedMetadata(), mockedIterator)

    verify(mockedIterator, times(2)).emit(any(classOf[Array[Object]]))
    verify(mockedIterator, times(2)).emit(anyString(), anyInt().asInstanceOf[JInt], anyLong().asInstanceOf[JLong])
    verify(mockedIterator, times(1)).emit(
      jsonMatcher("{\"col_str\": \"{'Value':'def'}\", \"col_int\": 7, \"col_long\": 17}"),
      ArgumentMatchers.eq(JInt.valueOf(0)),
      ArgumentMatchers.eq(JLong.valueOf(2))
    )
    verify(mockedIterator, times(1)).emit(
      jsonMatcher("{\"col_str\": \"{'Value':'xyz'}\", \"col_int\": 13, \"col_long\": 23}"),
      ArgumentMatchers.eq(JInt.valueOf(0)),
      ArgumentMatchers.eq(JLong.valueOf(3))
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
    val mockedIterator = mockExasolIterator(newProperties, Seq(0), Seq(-1))
    KafkaTopicDataImporter.run(getMockedMetadata(), mockedIterator)

    verify(mockedIterator, times(4)).emit(any(classOf[Array[Object]]))
    verify(mockedIterator, times(4)).emit(anyString(), anyInt().asInstanceOf[JInt], anyLong().asInstanceOf[JLong])
  }

  private[this] def getMockedMetadata(): ExaMetadata = {
    val meta = mock[ExaMetadata]
    when(meta.getOutputColumnCount()).thenReturn(3L)
    when(meta.getOutputColumnType(anyInt())).thenAnswer(new Answer[Class[_]]() {
      override def answer(invocation: InvocationOnMock): Class[_] = {
        val columnIndex = invocation.getArguments()(0).asInstanceOf[JInt]
        Seq(
          classOf[String],
          classOf[JInt],
          classOf[JLong]
        )(columnIndex)
      }
    })
    meta
  }

}
