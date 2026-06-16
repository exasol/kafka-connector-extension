package com.exasol.cloudetl.kafka;

public final class KafkaConnectorConstants {
    public static final int KEY_VALUE_PROPERTIES_INDEX = 0;
    public static final String ERROR_POLLING_TOPIC_DATA = "Error polling for Kafka topic {{TOPIC}} data. ";
    public static final String ERROR_READING_TOPIC_PARTITION = "Failed to read partition metadata for topic {{TOPIC}}.";
    public static final String TIMEOUT_ERROR_MESSAGE = "Timeout trying to connect to Kafka brokers.";
    public static final String TIMEOUT_ERROR_MITIGATION_1 = "Please ensure that there is a network connection between Kafka brokers and the Exasol datanode.";
    public static final String TIMEOUT_ERROR_MITIGATION_2 = "Similarly check that Kafka advertised listeners are reachable from Exasol cluster.";
    public static final String AUTHORIZATION_ERROR_MESSAGE = "A consumer or a consumer group does not have access to read the given topic. Cause: {{CAUSE}}";
    public static final String AUTHORIZATION_ERROR_MITIGATION = "Please make sure that the topic is readable by this consumer or consumer groups";
    public static final String AUTHENTICATION_ERROR_MESSAGE = "Failed to authenticate to the Kafka cluster. Cause: {{CAUSE}}";
    public static final String AUTHENTICATION_ERROR_MITIGATION = "Please ensure that SASL credentials and mechanisms are correct for authentication.";

    private KafkaConnectorConstants() {
    }
}
