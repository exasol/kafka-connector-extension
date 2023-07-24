package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException
import com.exasol.cloudetl.kafka.KafkaConsumerProperties
import com.exasol.errorreporting.ExaError

import org.apache.kafka.common.serialization.Deserializer

object StringDeserialization extends RecordDeserialization {

  override def getDeserializer(
    properties: KafkaConsumerProperties,
    fieldSpecs: Seq[FieldSpecification]
  ): Deserializer[Map[FieldSpecification, Seq[Any]]] =
    if (
      fieldSpecs.exists {
        case _: FullRecord => false
        case _             => true
      }
    ) {
      throw new KafkaConnectorException(
        ExaError
          .messageBuilder("E-KCE-18")
          .message("String deserialization can only use full record format specification 'key' or 'value'.")
          .mitigation("Please check that record specification does not contains field selections.")
          .toString()
      )
    } else {
      new AsStringDeserializer(fieldSpecs)
    }
}
