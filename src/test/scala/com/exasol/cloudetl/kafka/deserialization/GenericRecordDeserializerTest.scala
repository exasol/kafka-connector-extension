package com.exasol.cloudetl.kafka.deserialization

import java.lang.{Integer => JInt}
import java.util.Collections

import com.exasol.common.json.JsonMapper

import com.fasterxml.jackson.databind.JsonNode
import org.apache.avro.SchemaBuilder
import org.apache.avro.generic.{GenericRecord, GenericRecordBuilder}
import org.apache.kafka.common.serialization.Deserializer
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class GenericRecordDeserializerTest extends AnyFunSuite with MockitoSugar {

  private[this] val schema = SchemaBuilder
    .record("test")
    .fields()
    .optionalString("field1")
    .requiredLong("field2")
    .name("complex")
    .`type`(SchemaBuilder.array().items().intType())
    .withDefault(Collections.emptyList[JInt])
    .endRecord()

  private[this] def extractFrom(
    record: GenericRecord,
    fieldList: Seq[FieldSpecification]
  ): Map[FieldSpecification, Seq[Any]] = {
    val derse = mock[Deserializer[GenericRecord]]
    when(derse.deserialize(ArgumentMatchers.anyString(), ArgumentMatchers.any[Array[Byte]]))
      .thenReturn(record)
    new GenericRecordDeserializer(fieldList, derse).deserialize("", Array.empty[Byte])
  }

  test("must give all fields from record if field list is not provided") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field1", "val1")
        .set("field2", 11L)
        .set("complex", Array(1, 2, 3))
        .build(),
      Seq(RecordValueFields)
    )

    assert(row === Map(RecordValueFields -> Seq[Any]("val1", 11L, "[1,2,3]")))
  }

  test("must only use fields provided to deserializer in the right order") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field1", "val1")
        .set("field2", 11L)
        .set("complex", Array(1, 2, 3))
        .build(),
      Seq(RecordValueField("complex"), RecordValueField("field1"))
    )

    val expected = Map(
      RecordValueField("complex") -> Seq("[1,2,3]"),
      RecordValueField("field1") -> Seq("val1")
    )
    assert(row === expected)
  }

  test("must provide null values for fields not present and default values") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field2", 11L)
        .build(),
      Seq(RecordValueField("field1"), RecordValueField("field2"), RecordValueField("complex"))
    )

    val expected = Map(
      RecordValueField("field1") -> Seq(null),
      RecordValueField("field2") -> Seq(11L),
      RecordValueField("complex") -> Seq("[]")
    )
    assert(row === expected)
  }

  test("must return null for non-existent field to keep table structure") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field2", 11L)
        .build(),
      Seq(RecordValueField("field2"), RecordValueField("unknownField"))
    )

    val expected = Map(
      RecordValueField("field2") -> Seq(11L),
      RecordValueField("unknownField") -> Seq(null)
    )
    assert(row === expected)
  }

  test("must serialize the record as full json when requested") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field1", "val1")
        .set("field2", 11L)
        .set("complex", Array(1, 2, 3))
        .build(),
      Seq(RecordValue)
    )

    assert(row.size === 1)
    assert(row.contains(RecordValue))
    assert(row(RecordValue).size === 1)
    assert(row(RecordValue).headOption.getOrElse("").isInstanceOf[String])

    val jsonValueInRow = row(RecordValue).headOption.map(_.asInstanceOf[String]).getOrElse("")
    val expectedJson = JsonMapper.parseJson[JsonNode](
      """|{"field1": "val1",
         |"field2": 11,
         |"complex": [1,2,3]}
         |""".stripMargin
    )
    assert(JsonMapper.parseJson[JsonNode](jsonValueInRow) === expectedJson)
  }

}
