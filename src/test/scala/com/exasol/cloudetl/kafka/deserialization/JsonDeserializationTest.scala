package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException
import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.scalatest.funsuite.AnyFunSuite

class JsonDeserializationTest extends AnyFunSuite {

  test("must create a JSON deserializer for concrete fields") {
    val deserializer = JsonDeserialization.getDeserializer(
      new KafkaConsumerProperties(Map.empty),
      Seq(new RecordValueField("user_id"))
    )

    assert(deserializer.isInstanceOf[JsonDeserializer])
  }

  test("must reject wildcard field selection for JSON") {
    val thrown = intercept[KafkaConnectorException] {
      JsonDeserialization.getDeserializer(
        new KafkaConsumerProperties(Map.empty),
        Seq(RecordValueFields)
      )
    }

    assert(thrown.getMessage.contains("E-KCE-16"))
    assert(thrown.getMessage.contains("not supported for JSON"))
  }
}
