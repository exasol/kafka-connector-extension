package com.exasol.cloudetl.kafka.deserialization

import org.apache.kafka.common.serialization.{Deserializer, StringDeserializer}

/**
 * Emits the string representation record as single element sequence.
 */
class AsStringDeserializer(fieldSpecs: Seq[FieldSpecification])
    extends Deserializer[Map[FieldSpecification, Seq[Any]]] {

  private val deserializer = new StringDeserializer

  final override def deserialize(
    topic: String,
    data: Array[Byte]
  ): Map[FieldSpecification, Seq[Any]] =
    fieldSpecs.map(fieldSpec => (fieldSpec, Seq(deserializer.deserialize(topic, data)))).toMap
}
