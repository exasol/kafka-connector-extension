package com.exasol.cloudetl.kafka.serde

import org.apache.kafka.common.serialization.Serdes

trait PrimitiveSerdes {

  implicit val stringValueSerde = Serdes.String().asInstanceOf[ValueSerde[String]]

  // Other types (int, long, float, double) can be added similarly when required

}

object PrimitiveSerdes {
  object Implicits extends PrimitiveSerdes
}
