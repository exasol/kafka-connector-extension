package com.exasol.cloudetl.kafka.deserialization

import java.lang.{Integer => JInt}
import java.util.Collections

import com.exasol.common.json.JsonMapper

import com.fasterxml.jackson.databind.{JsonNode, ObjectMapper}
import org.apache.avro.SchemaBuilder
import org.apache.avro.generic.{GenericRecord, GenericRecordBuilder}
import org.apache.kafka.common.serialization.Deserializer
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar

@SuppressWarnings(Array("org.wartremover.warts.Nothing"))
class GenericRecordDeserializerTest extends AnyFunSuite with Matchers with MockitoSugar {

  private val schema = SchemaBuilder
    .record("test")
    .fields()
    .optionalString("field1")
    .requiredLong("field2")
    .name("complex")
    .`type`(SchemaBuilder.array().items().intType())
    .withDefault(Collections.emptyList[JInt])
    .endRecord()

  private def extractFrom(
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

    row must contain(RecordValueFields -> Seq[Any]("val1", 11L, "[1,2,3]"))
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
    row must have size 2
    row must contain(RecordValueField("complex") -> Seq("[1,2,3]"))
    row must contain(RecordValueField("field1") -> Seq("val1"))
  }

  test("must provide null values for fields not present and default values") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field2", 11L)
        .build(),
      Seq(RecordValueField("field1"), RecordValueField("field2"), RecordValueField("complex"))
    )

    row must have size 3
    row must contain(RecordValueField("field1") -> Seq(null))
    row must contain(RecordValueField("field2") -> Seq(11L))
    row must contain(RecordValueField("complex") -> Seq("[]"))
  }

  test("must return null for non-existent field to keep table structure") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field2", 11L)
        .build(),
      Seq(RecordValueField("field2"), RecordValueField("unknownField"))
    )

    row must have size 2
    row must contain(RecordValueField("field2") -> Seq(11L))
    row must contain(RecordValueField("unknownField") -> Seq(null))
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

    row must have size 1
    row must contain key RecordValue
    row(RecordValue) must have size 1
    row(RecordValue).headOption.getOrElse("") mustBe a[String]
    val jsonValueInRow = row(RecordValue).headOption.map(_.asInstanceOf[String]).getOrElse("")

    JsonMapper.parseJson[JsonNode](jsonValueInRow) must be(
      JsonMapper.parseJson[JsonNode]("""
                                       |{"field1": "val1",
                                       |"field2": 11,
                                       |"complex": [1,2,3]}
                                       |""".stripMargin)
    )
  }

}
