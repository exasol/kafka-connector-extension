package com.exasol.cloudetl.kafka;

import java.util.List;

import org.apache.kafka.common.security.auth.SecurityProtocol;

public final class KafkaConstants {
    public static final List<SecurityProtocol> SSL_PROTOCOLS = List.of(SecurityProtocol.SSL);
    public static final List<SecurityProtocol> SASL_PROTOCOLS = List.of(SecurityProtocol.SASL_PLAINTEXT,
            SecurityProtocol.SASL_SSL);

    private KafkaConstants() {
    }
}
