package com.exasol.cloudetl.kafka.serde

import com.sksamuel.avro4s.Decoder
import com.sksamuel.avro4s.Encoder
import com.sksamuel.avro4s.RecordFormat

trait AvroRecordFormat {
  implicit def avroRecordFormat[T: Encoder: Decoder]: RecordFormat[T] = RecordFormat[T]
}

object AvroRecordFormat {
  object Implicits extends AvroRecordFormat
}
