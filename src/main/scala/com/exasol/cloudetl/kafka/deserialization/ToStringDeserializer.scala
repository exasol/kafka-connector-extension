package com.exasol.cloudetl.kafka.deserialization

import org.apache.kafka.common.serialization.Deserializer

/**
 * Emits the string representation record as single element seq
 */
class ToStringDeserializer(delegate: Deserializer[_]) extends Deserializer[Seq[Any]] {
  final override def deserialize(topic: String, data: Array[Byte]): Seq[Any] =
    Seq(s"${delegate.deserialize(topic, data)}")
}
