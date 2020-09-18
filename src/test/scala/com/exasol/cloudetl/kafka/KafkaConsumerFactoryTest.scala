package com.exasol.cloudetl.kafka

import org.scalatest.BeforeAndAfterEach
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class KafkaConsumerFactoryTest extends AnyFunSuite with BeforeAndAfterEach with MockitoSugar {

  test("apply throws if required BOOTSTRAP_SERVERS property is not provided") {
    val thrown = intercept[IllegalArgumentException] {
      KafkaConsumerFactory(KafkaConsumerProperties(Map.empty[String, String]))
    }
    assert(thrown.getMessage === "Please provide a value for the BOOTSTRAP_SERVERS property!")
  }

  test("apply throws if required SCHEMA_REGISTRY_URL property is not provided") {
    val properties = Map("BOOTSTRAP_SERVERS" -> "kafka01.internal:9092")
    val thrown = intercept[IllegalArgumentException] {
      KafkaConsumerFactory(KafkaConsumerProperties(properties))
    }
    assert(thrown.getMessage === "Please provide a value for the SCHEMA_REGISTRY_URL property!")
  }

}
