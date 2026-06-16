package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException
import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.scalatest.funsuite.AnyFunSuite

class AvroDeserializationTest extends AnyFunSuite {

  test("must create a generic record deserializer when schema registry is configured") {
    val properties = new KafkaConsumerProperties(Map("SCHEMA_REGISTRY_URL" -> "http://schema-registry:8081"))

    val deserializer = AvroDeserialization.getDeserializer(properties, Seq(new RecordValueField("amount")))

    assert(deserializer.isInstanceOf[GenericRecordDeserializer])
  }

  test("must fail when schema registry is missing") {
    val thrown = intercept[KafkaConnectorException] {
      AvroDeserialization.getDeserializer(new KafkaConsumerProperties(Map.empty), Seq(new RecordValueField("amount")))
    }

    assert(thrown.getMessage.contains("E-KCE-17"))
    assert(thrown.getMessage.contains("Schema Registry URL is missing"))
  }
}
