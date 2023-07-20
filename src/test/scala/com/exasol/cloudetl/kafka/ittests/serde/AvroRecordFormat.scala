package com.exasol.cloudetl.kafka.serde

import com.sksamuel.avro4s.Encoder
import com.sksamuel.avro4s.Decoder
import com.sksamuel.avro4s.RecordFormat
import com.sksamuel.avro4s.SchemaFor

trait AvroRecordFormat {
  implicit def avroRecordFormat[T: Encoder: Decoder: SchemaFor]: RecordFormat[T] = RecordFormat[T]
}

object AvroRecordFormat {
  object Implicits extends AvroRecordFormat
}
