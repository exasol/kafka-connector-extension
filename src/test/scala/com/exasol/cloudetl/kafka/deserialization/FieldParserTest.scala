package com.exasol.cloudetl.kafka.deserialization

import org.scalatest.funsuite.AnyFunSuite

class FieldParserTest extends AnyFunSuite {

  test("must parse to correct fields") {
    assert(FieldParser.get(Seq("key", "value.*")) === Seq(RecordKey, RecordValueFields))
  }

  test("must parse to correct fields with timestamp and select") {
    val fields = Seq("key.field1", "timestamp", "value.*", "value.field2")
    val expectedFields =
      Seq(RecordKeyField("field1"), TimestampField, RecordValueFields, RecordValueField("field2"))
    assert(FieldParser.get(fields) === expectedFields)
  }
}
