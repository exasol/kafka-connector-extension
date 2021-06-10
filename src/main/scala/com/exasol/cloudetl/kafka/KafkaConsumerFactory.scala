package com.exasol.cloudetl.kafka

import java.nio.file.Files
import java.nio.file.Paths

import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.KafkaConsumerProperties._

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.Deserializer

/**
 * A factory class that creates Kafka consumer clients.
 */
object KafkaConsumerFactory {

  /**
   * Creates a [[org.apache.kafka.clients.consumer.KafkaConsumer]] from
   * [[KafkaConsumerProperties]] properties.
   *
   * At the moment Avro based specific {@code KafkaConsumer[String,
   * GenericRecord]} consumer is returned. Therefore, in order to define
   * the schema of [[org.apache.avro.generic.GenericRecord]] the {@code
   * SCHEMA_REGISTRY_URL} value should be provided.
   */
  def apply[K, V](
    properties: KafkaConsumerProperties,
    keyDeserializer: Deserializer[K],
    valueDeserializer: Deserializer[V]
  ): KafkaConsumer[K, V] = {
    val topic = properties.getTopic()
    try {
      new KafkaConsumer(
        properties.getProperties(),
        keyDeserializer,
        valueDeserializer
      )
    } catch {
      case exception: Throwable =>
        throw new KafkaConnectorException(
          s"Error creating a Kafka consumer for topic '$topic'. Cause: " + exception.getMessage(),
          exception
        )
    }
  }

}
