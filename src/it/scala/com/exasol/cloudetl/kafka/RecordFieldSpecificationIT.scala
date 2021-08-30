package com.exasol.cloudetl.kafka

import java.lang.{Integer => JInt, Long => JLong}

import scala.jdk.CollectionConverters.CollectionHasAsScala

import com.exasol.ExaMetadata
import com.exasol.common.json.JsonMapper

import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.mockito.{ArgumentCaptor, Mockito}
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mockito.{times, when}
import org.mockito.invocation.InvocationOnMock
import org.mockito.stubbing.Answer

/**
 * A test class that tests the {@code RECORD_FIELDS} integration and not the offset and partition
 * handling.
 */
class RecordFieldSpecificationIT extends KafkaTopicDataImporterAvroIT {

  private[this] val customRecord = AvroRecord("abc", 3, 13)

  implicit val stringSerializer = new StringSerializer

  private[this] def assertJson(actual: String, expected: String): Unit = {
    assert(JsonMapper.parseJson[JsonNode](actual) === JsonMapper.parseJson[JsonNode](expected))
    ()
  }

  private[this] def getEmittedValues(recordFieldsStmt: String, outputColumnTypes: Seq[Class[_]]): Seq[Any] = {
    val iter = mockExasolIterator(
      properties ++ Map("RECORD_FIELDS" -> recordFieldsStmt),
      Seq(0),
      Seq(-1)
    )
    val outputColumnTypesWithMeta = outputColumnTypes ++ Seq(classOf[JInt], classOf[JLong])
    val columnCount = outputColumnTypesWithMeta.size
    val meta = mock[ExaMetadata]
    when(meta.getOutputColumnCount()).thenReturn(columnCount)
    when(meta.getOutputColumnType(anyInt())).thenAnswer(
      new Answer[Class[_]]() {
        override def answer(invocation: InvocationOnMock): Class[_] = {
          val columnIndex = invocation.getArguments()(0).asInstanceOf[JInt]
          outputColumnTypesWithMeta(columnIndex)
        }
      })
    KafkaTopicDataImporter.run(meta, iter)

    val captor = ArgumentCaptor.forClass[Any, Any](classOf[Any])
    Mockito.verify(iter, times(1)).emit(captor.capture())

    val valuesEmitted = captor.getAllValues.asScala
    valuesEmitted.slice(0, valuesEmitted.size - 2).toSeq
  }

  test("default must be 'value.*': All fields from the record") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    assert(
      getEmittedValues("value.*", Seq(classOf[String], classOf[JInt], classOf[JLong])
    ) === Seq("abc", 3, 13))
  }

  test("must emit multiple record value fields in the order specified") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    assert(
      getEmittedValues("value.col_long, value.col_str", Seq(classOf[JLong], classOf[String])
      ) === Seq(13, "abc"))
  }

  test("must be able to reference the full value") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    val result = getEmittedValues("value", Seq(classOf[String]))
    assert(result.size === 1)
    assertJson(
      result(0).asInstanceOf[String],
      """ {
        |   "col_str": "abc",
        |   "col_int": 3,
        |   "col_long": 13
        | }
        |""".stripMargin
    )
  }

  test("must be able to reference key values with default RECORD_KEY_FORMAT string") {
    createCustomTopic(topic)
    publishToKafka(topic, "string_key", customRecord)
    assert(
      getEmittedValues("key, value.col_long", Seq(classOf[String], classOf[JLong])
      ) === Seq("string_key", 13))
  }

  test("must fail when the key is accessed with concrete field") {
    createCustomTopic(topic)
    publishToKafka(topic, "string_key", customRecord)
    intercept[KafkaConnectorException] {
      getEmittedValues("key.someFieldReference", Seq(classOf[String]))
    }
  }

  test("must fail when the key is accessed with the 'all fields' reference") {
    createCustomTopic(topic)
    publishToKafka(topic, "string_key", customRecord)
    intercept[KafkaConnectorException] {
      getEmittedValues("key.*", Seq())
    }
  }

  test("must handle null key and values") {
    createCustomTopic(topic)
    publishToKafka(topic, null.asInstanceOf[String], null.asInstanceOf[AvroRecord])
    assert(getEmittedValues("key, value", Seq(classOf[String], classOf[Any])) === Seq(null, null))
  }

  test("must handle null values combined with a present key and timestamps") {
    createCustomTopic(topic)
    publishToKafka(topic, "theKey", null.asInstanceOf[AvroRecord])

    val values = getEmittedValues(
      "key, timestamp, value",
      Seq(classOf[String], classOf[JLong], classOf[Any])
    )
    assert(values.size === 3)
    assert(values(0) === "theKey")
    assert(values(1).isInstanceOf[JLong])
    assert(values(2) === null)
  }

  test("must include record timestamp when in fieldlist") {
    createCustomTopic(topic)
    val recordTimestamp = 123456L
    withProducer[String, AvroRecord, Unit] { producer =>
      producer.send(
        new ProducerRecord[String, AvroRecord](
          topic,
          0,
          recordTimestamp,
          "record_key",
          customRecord
        )
      )
      ()
    }
    val values = getEmittedValues(
      "timestamp, value.str_col, key",
      Seq(classOf[JLong], classOf[Any], classOf[String])
    )
    assert(values === Seq(recordTimestamp, null, "record_key"))
  }
}
