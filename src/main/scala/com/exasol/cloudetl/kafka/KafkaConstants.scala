package com.exasol.cloudetl.kafka

import org.apache.kafka.common.security.auth.SecurityProtocol

object KafkaConstants {
  val SSL_PROTOCOLS: List[SecurityProtocol] = List(SecurityProtocol.SSL)
  val SASL_PROTOCOLS: List[SecurityProtocol] = List(SecurityProtocol.SASL_PLAINTEXT, SecurityProtocol.SASL_SSL)
}
