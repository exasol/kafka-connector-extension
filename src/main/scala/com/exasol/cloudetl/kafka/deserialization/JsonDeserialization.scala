package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.{KafkaConnectorException, KafkaConsumerProperties}

import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

/**
 * Creates deserializers for JSON records.
 */
object JsonDeserialization extends RecordDeserialization {

  override def getDeserializer(
    properties: KafkaConsumerProperties,
    fieldSpecs: Seq[FieldSpecification]
  ): Deserializer[Map[FieldSpecification, Seq[Any]]] =
    if (
      fieldSpecs.exists {
        case _: AllFieldsSpecification => true
        case _                         => false
      }
    ) {
      throw new KafkaConnectorException(
        "Referencing all fields with key.* or value.* is not supported for json " +
          "as the order is not deterministic."
      )
    } else {
      new JsonDeserializer(fieldSpecs, new StringDeserializer)
    }
}
