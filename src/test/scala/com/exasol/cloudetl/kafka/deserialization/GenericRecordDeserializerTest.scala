package com.exasol.cloudetl.kafka.deserialization

import java.lang.{Integer => JInt}
import java.util.Collections

import org.apache.avro.SchemaBuilder
import org.apache.avro.generic.{GenericRecord, GenericRecordBuilder}
import org.apache.kafka.common.serialization.Deserializer
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers
import org.scalatestplus.mockito.MockitoSugar

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
    fieldList: Option[Seq[String]]
  ): Seq[Any] = {
    val derse = mock[Deserializer[GenericRecord]]
    when(derse.deserialize(ArgumentMatchers.anyString(), ArgumentMatchers.any[Array[Byte]]))
      .thenReturn(record)
    new GenericRecordDeserializer(fieldList, derse).deserialize("", Array.empty[Byte])
  }

  test("Must give all fields from record if field list is not provided") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field1", "val1")
        .set("field2", 11L)
        .set("complex", Array(1, 2, 3))
        .build(),
      None
    )

    row must contain theSameElementsInOrderAs Seq("val1", 11L, """[1,2,3]""")
  }

  test("must only use fields provided to deserializer in the right order") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field1", "val1")
        .set("field2", 11L)
        .set("complex", Array(1, 2, 3))
        .build(),
      Option(Seq("complex", "field1"))
    )

    row must contain theSameElementsInOrderAs Seq("""[1,2,3]""", "val1")
  }

  test("must provide null values for fields not present and default values") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field2", 11L)
        .build(),
      Option(Seq("field1", "field2", "complex"))
    )

    row must contain theSameElementsInOrderAs Seq(null, 11L, "[]")
  }

  test("must return null for non-existent field to keep table structure") {
    val row = extractFrom(
      new GenericRecordBuilder(schema)
        .set("field2", 11L)
        .build(),
      Option(Seq("field2", "unknownField"))
    )

    row must contain theSameElementsInOrderAs Seq(11L, null)
  }
}
