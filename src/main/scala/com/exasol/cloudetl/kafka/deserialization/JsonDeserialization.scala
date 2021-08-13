package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.{KafkaConnectorException, KafkaConsumerProperties}
import com.exasol.errorreporting.ExaError

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
        ExaError
          .messageBuilder("E-KCE-16")
          .message(
            "Referencing all fields with key.* or value.* is not supported for JSON as the order is not deterministic."
          )
          .mitigation("Please use specific field references for JSON, for example, value.fieldName.")
          .toString()
      )
    } else {
      new JsonDeserializer(fieldSpecs, new StringDeserializer)
    }
}
