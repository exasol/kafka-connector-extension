package com.exasol.cloudetl.kafka

object KafkaConnectorConstants {

  val KEY_VALUE_PROPERTIES_INDEX: Int = 0

  val ERROR_POLLING_TOPIC_DATA =
    "Error polling for Kafka topic {{TOPIC}} data. "

  val ERROR_READING_TOPIC_PARTITION =
    "Failed to read partition metadata for topic {{TOPIC}}."

  val TIMEOUT_ERROR_MESSAGE =
    "Timeout trying to connect to Kafka brokers."
  val TIMEOUT_ERROR_MITIGATION_1 =
    "Please ensure that there is a network connection between Kafka brokers and the Exasol datanode."
  val TIMEOUT_ERROR_MITIGATION_2 =
    "Similarly check that Kafka advertised listeners are reachable from Exasol cluster."

  val AUTHORIZATION_ERROR_MESSAGE =
    "A consumer or a consumer group does not have access to read the given topic. Cause: "
  val AUTHORIZATION_ERROR_MITIGATION =
    "Please make sure that the topic is readable by this consumer or consumer groups"

  val AUTHENTICATION_ERROR_MESSAGE =
    "Failed to authenticate to the Kafka cluster. Cause: "
  val AUTHENTICATION_ERROR_MITIGATION =
    "Please ensure that SASL credentials and mechanisms are correct for authentication."

}
