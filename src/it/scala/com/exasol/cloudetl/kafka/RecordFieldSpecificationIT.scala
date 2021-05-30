package com.exasol.cloudetl.kafka

import com.exasol.ExaMetadata
import com.exasol.common.json
import com.exasol.common.json.JsonMapper
import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.common.serialization.StringSerializer
import org.mockito.Mockito.times
import org.mockito.{ArgumentCaptor, Mockito}
import org.scalatest.matchers.must.Matchers.{a, be, contain, convertToAnyMustWrapper, have}

import scala.jdk.CollectionConverters.CollectionHasAsScala

/**
 * A test that only tests the record_fields integration (not the offset and partition
 * handling)
 */
class RecordFieldSpecificationIT extends KafkaTopicDataImporterAvroIT {

  private val customRecord = AvroRecord("abc", 3, 13)

  implicit val stringSerializer = new StringSerializer

  private def assertJson(actual: String, expected: String): Unit = {
    JsonMapper.parseJson[JsonNode](actual) mustBe
      json.JsonMapper.parseJson[JsonNode](expected)
    ()
  }

  private def getEmittedValues(recordFieldsStmt: String): Seq[Any] = {
    val iter = mockExasolIterator(
      properties ++ Map(
        "RECORD_FIELDS" -> recordFieldsStmt
      ),
      Seq(0),
      Seq(-1)
    )
    KafkaTopicDataImporter.run(mock[ExaMetadata], iter)

    val captor = ArgumentCaptor.forClass[Any, Any](classOf[Any])
    Mockito
      .verify(iter, times(1))
      .emit(captor.capture())

    val valuesEmitted = captor.getAllValues.asScala
    valuesEmitted.slice(0, valuesEmitted.size - 2).toSeq
  }

  test("The default must be 'value': All fields from the record") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    getEmittedValues("value.*") must contain theSameElementsInOrderAs Seq("abc", 3, 13)
  }

  test("must emit multiple record value fields in the order specified") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    getEmittedValues("value.col_long, value.col_str") must
      contain theSameElementsInOrderAs Seq(13, "abc")
  }

  test("must be able to reference the full value") {
    createCustomTopic(topic)
    publishToKafka(topic, customRecord)
    val result = getEmittedValues("value")
    result must have size 1
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
    getEmittedValues("key, value.col_long") must
      contain theSameElementsInOrderAs Seq("string_key", 13)
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

  test("Must handle null key and values") {
    createCustomTopic(topic)
    publishToKafka(topic, null.asInstanceOf[String], null.asInstanceOf[AvroRecord])
    getEmittedValues("key, value") must
      contain theSameElementsInOrderAs Seq(null, null)
  }

  test("Must handle null values combined with a present key and timestamps") {
    createCustomTopic(topic)
    publishToKafka(topic, "theKey", null.asInstanceOf[AvroRecord])

    val values = getEmittedValues("key, timestamp, value")
    values must have size 3
    values(0) mustBe "theKey"
    values(1) mustBe a[java.lang.Long]
    values(2).asInstanceOf[String] mustBe null
    contain theSameElementsInOrderAs Seq("theKey", null)
  }

  test("must include record timestamp when in fieldlist") {
    createCustomTopic(topic)
    val recordTimestamp = 123456L

    withProducer[String, AvroRecord, Unit](
      producer => {
        val _ = producer.send(
          new ProducerRecord[String, AvroRecord](
            topic,
            0,
            recordTimestamp,
            "record_key",
            customRecord
          )
        )
      }
    )

    getEmittedValues("timestamp, value.str_col, key") must
      contain theSameElementsInOrderAs Seq(recordTimestamp, null, "record_key")
  }
}
