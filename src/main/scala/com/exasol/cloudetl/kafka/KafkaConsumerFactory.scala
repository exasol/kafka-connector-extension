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

  /**
   * Creates a [[org.apache.kafka.clients.consumer.KafkaConsumer]] from
   * [[KafkaConsumerProperties]] properties and [[ExaMetadata]]
   * metadata.
   *
   * The Exasol metadata is used to obtain additional secure key value
   * properties from connection object.
   */
  def apply[K, V](
    properties: KafkaConsumerProperties,
    keyDeserializer: Deserializer[K],
    valueDeserializer: Deserializer[V],
    exasolMetadata: ExaMetadata
  ): KafkaConsumer[K, V] = {
    validateNoSSLCredentials(properties)
    if (properties.hasNamedConnection()) {
      val newProperties = properties.mergeWithConnectionObject(exasolMetadata)
      validateSSLLocationFilesExist(newProperties)
      apply(newProperties, keyDeserializer, valueDeserializer)
    } else {
      apply(properties, keyDeserializer, valueDeserializer)
    }
  }

  private[this] def validateNoSSLCredentials(properties: KafkaConsumerProperties): Unit =
    if (properties.isSSLEnabled()) {
      val secureConnectionProperties = List(
        SSL_KEYSTORE_LOCATION,
        SSL_KEYSTORE_PASSWORD,
        SSL_KEY_PASSWORD,
        SSL_TRUSTSTORE_LOCATION,
        SSL_TRUSTSTORE_PASSWORD
      ).map(_.userPropertyName)
      if (secureConnectionProperties.exists(p => properties.containsKey(p))) {
        throw new KafkaConnectorException(
          "Please use a named connection object to provide secure SSL properties."
        )
      }
    }

  private[this] def validateSSLLocationFilesExist(properties: KafkaConsumerProperties): Unit =
    if (properties.isSSLEnabled()) {
      if (!Files.isRegularFile(Paths.get(properties.getSSLKeystoreLocation()))) {
        throw new KafkaConnectorException(
          s"Unable to find the SSL keystore file '${properties.getSSLKeystoreLocation()}'. " +
            s"Please make sure it is successfully uploaded to BucketFS bucket."
        )
      }
      if (!Files.isRegularFile(Paths.get(properties.getSSLTruststoreLocation()))) {
        throw new KafkaConnectorException(
          s"Unable to find the SSL truststore file '${properties.getSSLTruststoreLocation()}'. " +
            s"Please make sure it is successfully uploaded to BucketFS bucket."
        )
      }
    }
}
