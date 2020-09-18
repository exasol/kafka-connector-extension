package com.exasol.cloudetl.kafka

import java.nio.file.Files
import java.nio.file.Paths

import scala.collection.JavaConverters._

import com.exasol.ExaMetadata
import com.exasol.cloudetl.kafka.KafkaConsumerProperties._

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig
import io.confluent.kafka.serializers.KafkaAvroDeserializer
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.Deserializer
import org.apache.kafka.common.serialization.StringDeserializer

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
  def apply(properties: KafkaConsumerProperties): KafkaConsumer[String, GenericRecord] = {
    validateRequiredProperties(properties)
    validateSSLProperties(properties)
    val topic = properties.getTopic()
    try {
      new KafkaConsumer[String, GenericRecord](
        properties.getProperties(),
        new StringDeserializer,
        getAvroDeserializer(properties.getSchemaRegistryUrl())
          .asInstanceOf[Deserializer[GenericRecord]]
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
  def apply(
    properties: KafkaConsumerProperties,
    exasolMetadata: ExaMetadata
  ): KafkaConsumer[String, GenericRecord] = {
    val consumerProperties = properties.mergeWithConnectionObject(exasolMetadata)
    apply(consumerProperties)
  }

  private[this] def validateRequiredProperties(properties: KafkaConsumerProperties): Unit = {
    if (!properties.containsKey(BOOTSTRAP_SERVERS.userPropertyName)) {
      throw new IllegalArgumentException(
        s"Please provide a value for the ${BOOTSTRAP_SERVERS.userPropertyName} property!"
      )
    }
    if (!properties.hasSchemaRegistryUrl()) {
      throw new IllegalArgumentException(
        s"Please provide a value for the ${SCHEMA_REGISTRY_URL.userPropertyName} property!"
      )
    }
  }

  private[this] def validateSSLProperties(properties: KafkaConsumerProperties): Unit =
    if (properties.isSSLEnabled()) {
      if (!Files.isRegularFile(Paths.get(properties.getSSLKeystoreLocation()))) {
        throw new KafkaConnectorException(
          s"Unable to find the SSL keystore file '${properties.getSSLKeystoreLocation()}'."
        )
      }
      if (!Files.isRegularFile(Paths.get(properties.getSSLTruststoreLocation()))) {
        throw new KafkaConnectorException(
          s"Unable to find the SSL truststore file '${properties.getSSLTruststoreLocation()}'."
        )
      }
    }

  private[this] def getAvroDeserializer(schemaRegistryUrl: String): KafkaAvroDeserializer = {
    // The schema registry URL should be provided here since the one
    // configured in consumer properties is not for the deserializer.
    val deserializerConfig = Map(
      AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG -> schemaRegistryUrl
    )
    val kafkaAvroDeserializer = new KafkaAvroDeserializer
    kafkaAvroDeserializer.configure(deserializerConfig.asJava, false)
    kafkaAvroDeserializer
  }

}
