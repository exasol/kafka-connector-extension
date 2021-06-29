package com.exasol.cloudetl.kafka.consumer

import com.exasol.ExaIterator

/*
 * An interface for record consumer classes.
 */
trait RecordConsumer {

  /**
   * Emits records polled from Kafka as rows into Exasol table.
   *
   * @param iterator an Exasol iterator for emitting
   */
  def emit(iterator: ExaIterator): Unit

}
