package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConsumerProperties

import org.apache.kafka.common.serialization.Deserializer

/**
 * A factory for Kafka deserializers.
 */
trait RecordDeserialization {

  /**
   * Creates a deserializer that transforms the Kafka record into a columnar structure.
   * If a field list is provided, only those fields must be included in the sequence.
   */
  def getColumnDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]]

  /**
   * Creates a deserializer that emits the Kafka record as a plain JSON object.
   */
  def getSingleColumnJsonDeserializer(
    properties: KafkaConsumerProperties,
    fields: Option[Seq[String]]
  ): Deserializer[Seq[Any]]
}
