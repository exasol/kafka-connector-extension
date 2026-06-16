package com.exasol.cloudetl.kafka;

public class KafkaConnectorException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public KafkaConnectorException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public KafkaConnectorException(final String message) {
        super(message);
    }
}
