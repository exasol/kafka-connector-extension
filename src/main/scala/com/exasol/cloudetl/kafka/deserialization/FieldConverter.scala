package com.exasol.cloudetl.kafka.deserialization

import java.lang.{Long => JLong}
import java.sql.Timestamp

class FieldConverter(outputColumnTypes: Seq[Class[_]]) {

  final def convertRow(row: Seq[Any]): Seq[Any] =
    row.zipWithIndex.map(x => convert(outputColumnTypes(x._2), x._1))

  final def convert(columnType: Class[_], value: Any): Any =
    value match {
      case x: JLong if columnType == classOf[Timestamp] => new Timestamp(x)
      case _                                            => value
    }

}
