package com.exasol.cloudetl.kafka

import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serializer

sealed trait HasValueSerde

package object serde {
  type ValueSerde[T] = Serde[T] with HasValueSerde

  object ValueSerializer {
    def apply[T](implicit valueSerde: ValueSerde[T]): Serializer[T] = valueSerde.serializer()
  }
}
