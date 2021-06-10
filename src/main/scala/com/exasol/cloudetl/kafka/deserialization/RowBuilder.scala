package com.exasol.cloudetl.kafka.deserialization

import com.exasol.cloudetl.kafka.KafkaConnectorException

import org.apache.kafka.clients.consumer.ConsumerRecord

/**
 * Build the final row from the field specifications and the record data
 * points.
 */
object RowBuilder {

  /**
   * Extract the values specified by fieldSpecs from the record. Sinc
   */
  def buildRow(
    fieldSpecs: Seq[GlobalFieldSpecification],
    record: ConsumerRecord[Map[FieldSpecification, Seq[Any]], Map[FieldSpecification, Seq[Any]]],
    outputColumnCount: Int
  ): Seq[Any] = {
    val rowValues: Seq[Option[Seq[Any]]] = fieldSpecs.map {

      case keySpec: KeySpecification =>
        Option(record.key()) // check for null key
          .flatMap(_.get(keySpec)) // retrieve from key
          .orElse(defaultFor(keySpec)) // replace with default value if possible
      case valueSpec: ValueSpecification =>
        Option(record.value())
          .flatMap(_.get(valueSpec))
          .orElse(defaultFor(valueSpec)) // replace with default value if possible
      case TimestampField => Option(Seq(record.timestamp()))
      case unknown: GlobalFieldSpecification =>
        throw new KafkaConnectorException(s"Unsupported field specification $unknown")
    }

    val (absentValues, presentValues) = rowValues.partition(_.isEmpty)

    if (absentValues.nonEmpty) {
      // check if we can derive the number of null values needed
      if (absentValues.size > 1) {
        throw new KafkaConnectorException(
          "Can only replace a null record value with null " +
            "columns when exactly one expression like key.* and value.* is specified and not null"
        )
      } else {
        // we can handle the case when one field spec is null, e.g. the value and there is only
        // one reference like key.* or value.*: By using the output column count but we have to
        // keep the right order.
        val presentColumnCount = presentValues.map(_.map(_.size).getOrElse(0)).sum
        val valuesMissing = outputColumnCount - presentColumnCount

        rowValues.flatMap {
          case Some(x) => x
          case None    => Seq.fill[Any](valuesMissing) { null }
        }
      }
    } else {
      rowValues.flatMap(_.getOrElse(Seq.empty[Any]))
    }
  }

  /**
   * A default value of null can only be set for single value fieldSpecs.
   *
   * For expressions like key.* or value.* we cannot say how many null
   * values must be returned.
   */
  private[this] def defaultFor(fieldSpecification: FieldSpecification): Option[Seq[Any]] =
    fieldSpecification match {
      case _: AllFieldsSpecification => None
      case _                         => Option(Seq(null))
    }
}
