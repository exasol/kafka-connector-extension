package com.exasol.cloudetl.kafka.deserialization

import nl.jqno.equalsverifier.EqualsVerifier
import org.scalatest.funsuite.AnyFunSuite

class FieldSpecificationTest extends AnyFunSuite {

  test("equals and hashCode of record key field must follow the contract") {
    EqualsVerifier.forClass(classOf[RecordKeyField]).verify()
  }

  test("equals and hashCode of record value field must follow the contract") {
    EqualsVerifier.forClass(classOf[RecordValueField]).verify()
  }

  test("timestamp field must behave as a singleton for equals and hashCode") {
    assert(TimestampField === TimestampField)
    assert(TimestampField.hashCode === TimestampField.hashCode)
    assert(!TimestampField.equals(null))
  }
}
