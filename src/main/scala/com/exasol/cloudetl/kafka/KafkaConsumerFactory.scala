package com.exasol.cloudetl.kafka

import com.exasol.errorreporting.ExaError

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.KafkaException
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
      new KafkaConsumer(properties.getProperties(), keyDeserializer, valueDeserializer)
    } catch {
      case exception: KafkaException =>
        throw new KafkaConnectorException(
          ExaError
            .messageBuilder("F-KCE-1")
            .message("Could not create a Kafka consumer for topic {{TOPIC}}.", topic)
            .ticketMitigation()
            .toString(),
          exception
        )
    }
  }

}
