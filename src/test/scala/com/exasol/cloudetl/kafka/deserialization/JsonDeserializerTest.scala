package com.exasol.cloudetl.kafka.deserialization

import java.nio.charset.StandardCharsets

import org.apache.kafka.common.serialization.StringDeserializer
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers

@SuppressWarnings(Array("org.wartremover.warts.Nothing"))
class JsonDeserializerTest extends AnyFunSuite with Matchers {

  test("must deserialize json record with primitives") {
    val row = new JsonDeserializer(
      Seq(RecordValueField("number"), RecordValueField("string"), RecordValueField("bool")),
      new StringDeserializer
    ).deserialize(
      "randomTopic",
      """
        |{
        |  "number": 1,
        |  "string": "hello",
        |  "bool": true
        |}""".stripMargin.getBytes(StandardCharsets.UTF_8)
    )

    row must have size 3
    row must have size 3
    row must contain(RecordValueField("number") -> Seq(1))
    row must contain(RecordValueField("string") -> Seq("hello"))
    row must contain(RecordValueField("bool") -> Seq(true))
  }

  test("must convert complex json type to its string representation") {
    val row = new JsonDeserializer(
      Seq(RecordValueField("number"), RecordValueField("record")),
      new StringDeserializer
    ).deserialize(
      "randomTopic",
      """
        |{
        |  "number": 1,
        |  "record": {
        |   "field1": "value1",
        |   "field2": 23
        |   }
        |}""".stripMargin.getBytes(StandardCharsets.UTF_8)
    )

    row must have size 2
    row must contain(RecordValueField("number") -> Seq(1))
    row must contain(RecordValueField("record") -> Seq("""{"field1":"value1","field2":23}"""))
  }

  test("must only use fields provided to deserializer in the right order") {
    val row = new JsonDeserializer(
      Seq(RecordValueField("record"), RecordValueField("number")),
      new StringDeserializer
    ).deserialize(
      "randomTopic",
      """
        |{
        |  "number": 1,
        |  "fieldToIgnore": {
        |    "fieldA": 124,
        |    "fieldB": [true]
        |  },
        |  "record": {
        |   "field1": "value1",
        |   "field2": 23
        |   }
        |}""".stripMargin.getBytes(StandardCharsets.UTF_8)
    )

    row must have size 2
    row must contain(RecordValueField("number") -> Seq(1))
    row must contain(RecordValueField("record") -> Seq("""{"field1":"value1","field2":23}"""))
  }

  test("must provide null values for fields not present") {
    val row = new JsonDeserializer(
      Seq(RecordValueField("number"), RecordValueField("always_null_field")),
      new StringDeserializer
    ).deserialize(
      "randomTopic",
      """
        |{
        |  "number": 1
        |   }
        |}""".stripMargin.getBytes(StandardCharsets.UTF_8)
    )

    row must have size 2
    row must contain(RecordValueField("number") -> Seq(1))
    row must contain(RecordValueField("always_null_field") -> Seq(null))
  }
}
