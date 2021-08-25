package com.exasol.cloudetl.kafka.deserialization

import java.sql.{Date, Timestamp}

class FieldConverter(
  outputColumnTypes: Seq[Class[_]]
) {

  final def convertRow(row: Seq[Any]): Seq[Any] = {
    row.zipWithIndex map (x => convert(outputColumnTypes(x._2), x._1))
  }

  final def convert(column_type: Class[_], value: Any): Any = {
    if (column_type == classOf[Timestamp]) {
      value match {
        case x: Long => new Timestamp(x)
        case _ => value
      }
    } else {
      value
    }
  }
}
