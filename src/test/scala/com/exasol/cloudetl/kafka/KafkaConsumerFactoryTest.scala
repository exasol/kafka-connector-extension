package com.exasol.cloudetl.kafka

import com.exasol.ExaMetadata

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.VoidDeserializer
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KafkaConsumerFactoryTest extends AnyFunSuite with MockitoSugar {

  private[this] val defaultProperties: Map[String, String] = Map(
    "BOOTSTRAP_SERVERS" -> "localhost:6001",
    "SCHEMA_REGISTRY_URL" -> "http://localhost:6002",
    "TOPIC_NAME" -> "topic",
    "TABLE_NAME" -> "exasolTable"
  )

  test("apply returns a default kafka consumer type") {
    val consumerProperties = KafkaConsumerProperties(defaultProperties, mock[ExaMetadata])
    val kafkaConsumer = KafkaConsumerFactory(consumerProperties, new VoidDeserializer)
    assert(kafkaConsumer.isInstanceOf[KafkaConsumer[String, Void]])
  }

}
