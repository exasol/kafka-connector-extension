package com.exasol.cloudetl.kafka.deserialization

import java.nio.charset.StandardCharsets

import com.exasol.cloudetl.kafka.KafkaConnectorException
import com.exasol.common.json.JsonMapper

import com.fasterxml.jackson.databind.JsonNode
import org.apache.kafka.common.serialization.StringDeserializer
import org.scalatest.funsuite.AnyFunSuite

class JsonDeserializerTest extends AnyFunSuite {

  test("must deserialize json record with primitives") {
    val row = new JsonDeserializer(
      Seq(new RecordValueField("number"), new RecordValueField("string"), new RecordValueField("bool")),
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

    val expected = Map(
      new RecordValueField("number") -> Seq(1),
      new RecordValueField("string") -> Seq("hello"),
      new RecordValueField("bool") -> Seq(true)
    )
    assert(row === expected)
  }

  test("must convert complex json type to its string representation") {
    val row = new JsonDeserializer(
      Seq(new RecordValueField("number"), new RecordValueField("record")),
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

    val expected = Map(
      new RecordValueField("number") -> Seq(1),
      new RecordValueField("record") -> Seq("""{"field1":"value1","field2":23}""")
    )
    assert(row === expected)
  }

  test("must only use fields provided to deserializer in the right order") {
    val row = new JsonDeserializer(
      Seq(new RecordValueField("record"), new RecordValueField("number")),
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

    val expected = Map(
      new RecordValueField("number") -> Seq(1),
      new RecordValueField("record") -> Seq("""{"field1":"value1","field2":23}""")
    )
    assert(row === expected)
  }

  test("must provide null values for fields not present") {
    val row = new JsonDeserializer(
      Seq(new RecordValueField("number"), new RecordValueField("always_null_field")),
      new StringDeserializer
    ).deserialize(
      "randomTopic",
      """
        |{
        |  "number": 1
        |}""".stripMargin.getBytes(StandardCharsets.UTF_8)
    )

    val expected = Map(
      new RecordValueField("number") -> Seq(1),
      new RecordValueField("always_null_field") -> Seq(null)
    )
    assert(row === expected)
  }

  test("must fail when all fields are referenced") {
    intercept[KafkaConnectorException] {
      new JsonDeserializer(Seq(RecordValueFields), new StringDeserializer)
        .deserialize(
          "randomTopic",
          """
            |{
            |  "number": 1
            |}
            |""".stripMargin.getBytes(StandardCharsets.UTF_8)
        )
    }
  }

  test("must produce full json when the whole value is referenced") {
    val sourceRecord =
      """
        |{
        |  "number": 1,
        |  "record": {
        |   "field1": "value1",
        |   "field2": 23
        |   }
        |}""".stripMargin

    val row = new JsonDeserializer(
      Seq(RecordValue),
      new StringDeserializer
    ).deserialize(
      "randomTopic",
      sourceRecord.getBytes(StandardCharsets.UTF_8)
    )

    assert(row.size === 1)
    assert(row.contains(RecordValue))
    val values = row(RecordValue)
    assert(values.size === 1)
    val json = values.headOption.map(_.asInstanceOf[String]).getOrElse("")
    assert(JsonMapper.fromJson[JsonNode](json) === JsonMapper.fromJson[JsonNode](sourceRecord))
  }
}
