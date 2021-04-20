package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.{KafkaConnectorException, KafkaConsumerProperties}

import io.confluent.kafka.serializers.{AbstractKafkaAvroSerDeConfig, KafkaAvroDeserializer}
import org.apache.avro.generic.GenericRecord
import org.apache.kafka.common.serialization.Deserializer
import scala.jdk.CollectionConverters.MapHasAsJava

/**
 * Creates deserializers for avro records that are serialized with the confluent schema registry
 */
object AvroDeserialization extends RecordDeserialization {

  override def getColumnDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]] =
    new GenericRecordDeserializer(fields, getAvroDeserializer(properties.getSchemaRegistryUrl()))

  override def getSingleColumnJsonDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]] =
    if (properties.hasSchemaRegistryUrl()) {
      new ToStringDeserializer(getAvroDeserializer(properties.getSchemaRegistryUrl()))
    } else {
      throw new KafkaConnectorException(
        "SCHEMA_REGISTRY_URL must be provided for record type 'avro'"
      )
    }

  private[this] def getAvroDeserializer(
    schemaRegistryUrl: String
  ): Deserializer[GenericRecord] = {
    // The schema registry URL should be provided here since the one
    // configured in consumer properties is not for the deserializer.
    val deserializerConfig = Map(
      AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG -> schemaRegistryUrl
    )
    val kafkaAvroDeserializer = new KafkaAvroDeserializer
    kafkaAvroDeserializer.configure(deserializerConfig.asJava, false)
    kafkaAvroDeserializer.asInstanceOf[Deserializer[GenericRecord]]
  }

}
