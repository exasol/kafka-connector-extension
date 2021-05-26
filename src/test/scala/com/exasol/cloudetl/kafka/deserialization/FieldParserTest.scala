package com.exasol.cloudetl.kafka.deserialization

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.{contain, convertToAnyMustWrapper}

class FieldParserTest extends AnyFunSuite {

  test("Must map to correct fields 1") {
    FieldParser.get(Seq("key", "value.*")) must contain theSameElementsInOrderAs
      Seq(RecordKey, RecordValueFields)
  }

  test("Must parse correct fields 2") {
    FieldParser.get(
      Seq(
        "key.field1",
        "timestamp",
        "value.*",
        "value.field2"
      )
    ) must contain theSameElementsInOrderAs Seq(
      RecordKeyField("field1"),
      TimestampField,
      RecordValueFields,
      RecordValueField("field2")
    )
  }
}
