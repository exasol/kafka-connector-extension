package com.exasol.cloudetl.kafka.deserialization

import scala.jdk.CollectionConverters.MapHasAsJava

import com.exasol.cloudetl.kafka.{KafkaConnectorException, KafkaConsumerProperties}
import com.exasol.errorreporting.ExaError

import io.confluent.kafka.serializers.{AbstractKafkaSchemaSerDeConfig, KafkaAvroDeserializer}
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Deserializer

/**
 * Creates deserializers for avro records that are serialized with the Confluent schema registry.
 */
object AvroDeserialization extends RecordDeserialization {

  override def getDeserializer(
    properties: KafkaConsumerProperties,
    fieldSpecs: Seq[FieldSpecification]
  ): Deserializer[Map[FieldSpecification, Seq[Any]]] =
    if (properties.hasSchemaRegistryUrl()) {
      new GenericRecordDeserializer(fieldSpecs, getAvroDeserializer(properties.getSchemaRegistryUrl()))
    } else {
      throw new KafkaConnectorException(
        ExaError
          .messageBuilder("E-KCE-17")
          .message("Required Schema Registry URL is missing for Avro records.")
          .mitigation("Please provide URL using SCHEMA_REGISTRY_URL property.")
          .toString()
      )
    }

  private[this] def getAvroDeserializer(schemaRegistryUrl: String): Deserializer[GenericRecord] = {
    // The schema registry URL should be provided here since the one
    // configured in consumer properties is not for the deserializer.
    val deserializerConfig = Map(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG -> schemaRegistryUrl)
    val kafkaAvroDeserializer = new KafkaAvroDeserializer
    kafkaAvroDeserializer.configure(deserializerConfig.asJava, false)
    kafkaAvroDeserializer.asInstanceOf[Deserializer[GenericRecord]]
  }

}
