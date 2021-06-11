package com.exasol.cloudetl.kafka.deserialization

import org.scalatest.funsuite.AnyFunSuite

class FieldParserTest extends AnyFunSuite {

  test("must parse list of field specs to correct fields") {
    assert(FieldParser.get(Seq("key", "value.*")) === Seq(RecordKey, RecordValueFields))
  }

  test("must parse field spec string string to correct fields") {
    assert(FieldParser.get("key, value.*") === Seq(RecordKey, RecordValueFields))
  }

  test("must parse to correct fields with timestamp and select") {
    val fields = Seq("key.field1", "timestamp", "value.*", "value.field2", "value.Field_3")
    val expectedFields =
      Seq(
        RecordKeyField("field1"),
        TimestampField,
        RecordValueFields,
        RecordValueField("field2"),
        RecordValueField("Field_3")
      )
    assert(FieldParser.get(fields) === expectedFields)
  }

  test("must parse to correct fields for names matching [A-Za-z_][A-Za-z0-9_]") {
    val fields = "key._key1, value.FirstValue, value.secondValue, value.value_3"
    val expectedFields =
      Seq[GlobalFieldSpecification](
        RecordKeyField("_key1"),
        RecordValueField("FirstValue"),
        RecordValueField("secondValue"),
        RecordValueField("value_3")
      )
    assert(FieldParser.get(fields) === expectedFields)
  }
}
