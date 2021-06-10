package com.exasol.cloudetl.kafka

import com.exasol.ExaMetadata
import com.exasol.common.json
import com.exasol.common.json.JsonMapper
import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.mockito.Mockito.times
import org.mockito.{ArgumentCaptor, Mockito}

import scala.jdk.CollectionConverters.CollectionHasAsScala

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

  private[this] def getEmittedValues(recordFieldsStmt: String): Seq[Any] = {
    val iter = mockExasolIterator(
      properties ++ Map("RECORD_FIELDS" -> recordFieldsStmt),
      Seq(0),
      Seq(-1)
    )
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    val captor = ArgumentCaptor.forClass[Any, Any](classOf[Any])
    Mockito.verify(iter, times(1)).emit(captor.capture())

    val valuesEmitted = captor.getAllValues.asScala
    valuesEmitted.slice(0, valuesEmitted.size - 2).toSeq
  }

  test("default must be 'value.*': All fields from the record") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    assert(getEmittedValues("value.*") === Seq("abc", 3, 13))
  }

  test("must emit multiple record value fields in the order specified") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    assert(getEmittedValues("value.col_long, value.col_str") === Seq(13, "abc"))
  }

  test("must be able to reference the full value") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    val result = getEmittedValues("value")
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
    assert(getEmittedValues("key, value.col_long") === Seq("string_key", 13))
  }

  test("must fail when the key is accessed with concrete field") {
    createCustomTopic(topic)
    publishToKafka(topic, "string_key", customRecord)
    intercept[KafkaConnectorException] {
      getEmittedValues("key.someFieldReference")
    }
  }

  test("must fail when the key is accessed with the 'all fields' reference") {
    createCustomTopic(topic)
    publishToKafka(topic, "string_key", customRecord)
    intercept[KafkaConnectorException] {
      getEmittedValues("key.*")
    }
  }

  test("must handle null key and values") {
    createCustomTopic(topic)
    publishToKafka(topic, null.asInstanceOf[String], null.asInstanceOf[AvroRecord])
    assert(getEmittedValues("key, value") === Seq(null, null))
  }

  test("must handle null values combined with a present key and timestamps") {
    createCustomTopic(topic)
    publishToKafka(topic, "theKey", null.asInstanceOf[AvroRecord])

    val values = getEmittedValues("key, timestamp, value")
    assert(values.size === 3)
    assert(values(0) === "theKey")
    assert(values(1).isInstanceOf[java.lang.Long])
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
    val values = getEmittedValues("timestamp, value.str_col, key")
    assert(values === Seq(recordTimestamp, null, "record_key"))
  }
}
