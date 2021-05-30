package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.{a, an, convertToAnyMustWrapper}

class DeserializationFactoryTest extends AnyFunSuite {

  test(
    "must provide default serializers: " +
      "string as default for key and avro for values when key fields are specified"
  ) {
    val properties = new KafkaConsumerProperties(Map("SCHEMA_REGISTRY_URL" -> "someUrl"))
    val deserializers =
      DeserializationFactory.getSerializers(Seq(RecordKey, RecordValueFields), properties)

    deserializers.keyDeserializer mustBe an[AsStringDeserializer]
    deserializers.valueDeserializer mustBe a[GenericRecordDeserializer]
  }

  test("must not deserialize key when not specified in fieldSpecs") {
    val properties = new KafkaConsumerProperties(Map("RECORD_FORMAT" -> "json"))
    val deserializers = DeserializationFactory.getSerializers(Seq(RecordValue), properties)

    deserializers.keyDeserializer mustBe IgnoreKeyDeserializer
    deserializers.valueDeserializer mustBe a[JsonDeserializer]
  }

  test("key must be ignored when ot requested") {
    val properties = new KafkaConsumerProperties(
      Map("RECORD_KEY_FORMAT" -> "avro", "RECORD_VALUE_FORMAT" -> "string")
    )
    val deserializers = DeserializationFactory.getSerializers(Seq(RecordValue), properties)

    deserializers.keyDeserializer mustBe IgnoreKeyDeserializer
    deserializers.valueDeserializer mustBe an[AsStringDeserializer]
  }

  test("key must be taken into account when ot requested") {
    val properties = new KafkaConsumerProperties(
      Map("RECORD_KEY_FORMAT" -> "json", "RECORD_VALUE_FORMAT" -> "string")
    )
    val deserializers = DeserializationFactory.getSerializers(
      Seq(RecordKeyField("someField"), RecordValue),
      properties
    )
    deserializers.keyDeserializer mustBe a[JsonDeserializer]
    deserializers.valueDeserializer mustBe an[AsStringDeserializer]
  }
}
