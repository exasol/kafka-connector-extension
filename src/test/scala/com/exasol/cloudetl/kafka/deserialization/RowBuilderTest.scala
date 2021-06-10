package com.exasol.cloudetl.kafka.deserialization

import java.time.Instant

import com.exasol.cloudetl.kafka.KafkaConnectorException

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.mockito.Mockito.when
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.mockito.MockitoSugar

class RowBuilderTest extends AnyFunSuite with MockitoSugar {

  private[this] val TIMESTAMP = Instant.now().toEpochMilli()

  private[this] def record(
    key: Map[FieldSpecification, Seq[Any]],
    value: Map[FieldSpecification, Seq[Any]]
  ): ConsumerRecord[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]] = {
    val record =
      mock[ConsumerRecord[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]]]

    when(record.key()).thenReturn(key)
    when(record.value()).thenReturn(value)
    when(record.timestamp()).thenReturn(TIMESTAMP)
    record
  }

  test("must emit record key and value") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue),
      record(Map(RecordKey -> Seq("key")), Map(RecordValue -> Seq("value"))),
      0
    )
    assert(row === Seq("key", "value"))
  }

  test("must emit record timestamp, key and value") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue, TimestampField),
      record(Map(RecordKey -> Seq("key")), Map(RecordValue -> Seq("value"))),
      0
    )
    assert(row === Seq("key", "value", TIMESTAMP))
  }

  test("must emit null for key when key is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue),
      record(null, Map(RecordValue -> Seq("value"))),
      0
    )
    assert(row === Seq(null, "value"))
  }

  test("must emit null for value when value is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValue),
      record(Map(RecordKey -> Seq("key")), null),
      0
    )
    assert(row === Seq("key", null))
  }

  test("must emit null for values for concrete fields") {
    val row = RowBuilder.buildRow(
      Seq(RecordValueField("field1"), RecordValueField("field2")),
      record(null, Map(RecordValueField("field1") -> Seq("value1"))),
      0
    )
    assert(row === Seq("value1", null))
  }

  test("must emit null columns when all fields from record value are null") {
    val row = RowBuilder.buildRow(
      Seq(RecordValueFields),
      record(null, null),
      2
    )
    assert(row === Seq(null, null))
  }

  test("must be able to combine presents field with all field reference") {
    val row = RowBuilder.buildRow(
      Seq(RecordValueFields, TimestampField),
      record(null, Map(RecordValueFields -> Seq(1, 2, 3))),
      2
    )
    assert(row === Seq(1, 2, 3, TIMESTAMP))
  }

  test("must emit correct column count when record value is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKey, RecordValueFields, TimestampField),
      record(Map(RecordKey -> Seq("ourKey")), null),
      4
    )
    assert(row === Seq("ourKey", null, null, TIMESTAMP))
  }

  test("must work with two all fields references") {
    val row = RowBuilder.buildRow(
      Seq(RecordKeyFields, TimestampField, RecordValueFields),
      record(Map(RecordKeyFields -> Seq("key1")), Map(RecordValueFields -> Seq("val1", "val2"))),
      4
    )
    assert(row === Seq("key1", TIMESTAMP, "val1", "val2"))
  }

  test("must work with two all fields references when one is null") {
    val row = RowBuilder.buildRow(
      Seq(RecordKeyFields, TimestampField, RecordValueFields),
      record(null, Map(RecordValueFields -> Seq("val1", "val2"))),
      5
    )
    assert(row === Seq(null, null, TIMESTAMP, "val1", "val2"))
  }

  test("must fail when with two all fields references and null value") {
    intercept[KafkaConnectorException] {
      RowBuilder.buildRow(
        Seq(RecordKeyFields, TimestampField, RecordValueFields),
        record(null, null),
        4
      )
    }
  }

}
