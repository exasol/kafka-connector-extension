package com.exasol.cloudetl.kafka

import com.exasol.ExaMetadata

import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.KafkaConsumer

class KafkaConsumerFactoryIT extends KafkaIntegrationTest {

  test("apply returns a KafkaConsumer[String, GenericRecord]") {
    val consumerProperties = KafkaConsumerProperties(properties)
    val kafkaConsumer = KafkaConsumerFactory(consumerProperties, mock[ExaMetadata])
    assert(kafkaConsumer.isInstanceOf[KafkaConsumer[String, GenericRecord]])
  }

}
