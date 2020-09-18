package com.exasol.cloudetl.kafka

import com.exasol.ExaMetadata

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KafkaConsumerFactoryTest extends AnyFunSuite with MockitoSugar {

  test("apply returns a default kafka consumer type") {
    val properties = Map(
      "BOOTSTRAP_SERVERS" -> "localhost:6001",
      "SCHEMA_REGISTRY_URL" -> "http://localhost:6002",
      "TOPIC_NAME" -> "topic",
      "TABLE_NAME" -> "exasolTable"
    )
    val consumerProperties = KafkaConsumerProperties(properties)
    val kafkaConsumer = KafkaConsumerFactory(consumerProperties, mock[ExaMetadata])
    assert(kafkaConsumer.isInstanceOf[KafkaConsumer[String, GenericRecord]])
  }

}
