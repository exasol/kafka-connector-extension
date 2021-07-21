package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.{KafkaConnectorException, KafkaConsumerProperties}

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
        "Record format 'string' can only use the full 'key' or 'value' as specification"
      )
    } else {
      new AsStringDeserializer(fieldSpecs)
    }
}
