package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.scalatest.funsuite.AnyFunSuite

class DeserializationFactoryTest extends AnyFunSuite {

  test("must provide default serializers: string key and avro value, fields not specified") {
    val properties = new KafkaConsumerProperties(Map("SCHEMA_REGISTRY_URL" -> "someUrl"))
    val deserializers =
      DeserializationFactory.getSerializers(Seq(RecordKey, RecordValueFields), properties)

    assert(deserializers.keyDeserializer.isInstanceOf[AsStringDeserializer])
    assert(deserializers.valueDeserializer.isInstanceOf[GenericRecordDeserializer])
  }

  test("must not deserialize key when not specified in fieldSpecs") {
    val properties = new KafkaConsumerProperties(Map("RECORD_FORMAT" -> "json"))
    val deserializers = DeserializationFactory.getSerializers(Seq(RecordValue), properties)

    assert(deserializers.keyDeserializer === IgnoreKeyDeserializer)
    assert(deserializers.valueDeserializer.isInstanceOf[JsonDeserializer])
  }

  test("key must be ignored when ot requested") {
    val params = Map("RECORD_KEY_FORMAT" -> "avro", "RECORD_VALUE_FORMAT" -> "string")
    val properties = new KafkaConsumerProperties(params)
    val deserializers = DeserializationFactory.getSerializers(Seq(RecordValue), properties)

    assert(deserializers.keyDeserializer === IgnoreKeyDeserializer)
    assert(deserializers.valueDeserializer.isInstanceOf[AsStringDeserializer])
  }

  test("key must be taken into account when ot requested") {
    val params = Map("RECORD_KEY_FORMAT" -> "json", "RECORD_VALUE_FORMAT" -> "string")
    val properties = new KafkaConsumerProperties(params)
    val deserializers = DeserializationFactory.getSerializers(
      Seq(RecordKeyField("someField"), RecordValue),
      properties
    )

    assert(deserializers.keyDeserializer.isInstanceOf[JsonDeserializer])
    assert(deserializers.valueDeserializer.isInstanceOf[AsStringDeserializer])
  }
}
