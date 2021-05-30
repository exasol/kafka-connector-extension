package com.exasol.cloudetl.kafka.deserialization

import java.time.Instant

import com.exasol.cloudetl.kafka.KafkaConnectorException

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.must.Matchers.{contain, convertToAnyMustWrapper, have}
import org.scalatestplus.mockito.MockitoSugar

class RowBuilderTest extends AnyFunSuite with MockitoSugar {

  private val Timestamp = Instant.now().toEpochMilli

  private def record(
    key: Map[FieldSpecification, Seq[Any]],
    value: Map[FieldSpecification, Seq[Any]]
  ): ConsumerRecord[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]] = {
    val record =
      mock[ConsumerRecord[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]]]

    when(record.key()).thenReturn(key)
    when(record.value()).thenReturn(value)
    when(record.timestamp()).thenReturn(Timestamp)
    record
  }

  test("Must emit record key and value") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue),
      record(Map(RecordKey -> Seq("key")), Map(RecordValue -> Seq("value"))),
      0
    )
    row must have size 2
    row must contain theSameElementsInOrderAs Seq("key", "value")
  }

  test("Must emit record timestamp, key and value") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue, TimestampField),
      record(Map(RecordKey -> Seq("key")), Map(RecordValue -> Seq("value"))),
      0
    )
    row must have size 3
    row must contain theSameElementsInOrderAs Seq("key", "value", Timestamp)
  }

  test("Must emit null for key when key is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue),
      record(null, Map(RecordValue -> Seq("value"))),
      0
    )
    row must have size 2
    row must contain theSameElementsInOrderAs Seq(null, "value")
  }

  test("Must emit null for value when value is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue),
      record(Map(RecordKey -> Seq("key")), null),
      0
    )
    row must have size 2
    row must contain theSameElementsInOrderAs Seq("key", null)
  }

  test("Must emit null for values for concrete fields") {
    val row = RowBuilder.buildRow(
      Seq(RecordValueField("field1"), RecordValueField("field2")),
      record(null, Map(RecordValueField("field1") -> Seq("value1"))),
      0
    )
    row must have size 2
    row must contain theSameElementsInOrderAs Seq("value1", null)
  }

  test(
    "Must emit null for values when all fields from record value are requested" +
      "value is null"
  ) {
    val row = RowBuilder.buildRow(
      Seq(RecordValueFields),
      record(null, null),
      2
    )
    row must have size 2
    row must contain theSameElementsInOrderAs Seq(null, null)
  }

  test("Must be able to combine presents field with all field reference") {
    val row = RowBuilder.buildRow(
      Seq(RecordValueFields, TimestampField),
      record(null, Map(RecordValueFields -> Seq(1, 2, 3))),
      2
    )
    row must have size 4
    row must contain theSameElementsInOrderAs Seq(1, 2, 3, Timestamp)
  }

  test("Must emit correct column count when record value is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValueFields, TimestampField),
      record(Map(RecordKey -> Seq("ourKey")), null),
      4
    )
    row must have size 4
    row must contain theSameElementsInOrderAs Seq("ourKey", null, null, Timestamp)
  }

  test("Must work with two all fields references") {
    val row = RowBuilder.buildRow(
      Seq(RecordKeyFields, TimestampField, RecordValueFields),
      record(Map(RecordKeyFields -> Seq("key1")), Map(RecordValueFields -> Seq("val1", "val2"))),
      4
    )
    row must have size 4
    row must contain theSameElementsInOrderAs Seq("key1", Timestamp, "val1", "val2")
  }

  test("Must work with two all fields references when one is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKeyFields, TimestampField, RecordValueFields),
      record(null, Map(RecordValueFields -> Seq("val1", "val2"))),
      5
    )
    row must have size 5
    row must contain theSameElementsInOrderAs Seq(null, null, Timestamp, "val1", "val2")
  }

  test("Must fail when with two all fields references and null value") {
    intercept[KafkaConnectorException] {
      RowBuilder.buildRow(
        Seq(RecordKeyFields, TimestampField, RecordValueFields),
        record(null, null),
        4
      )
    }
  }

}
