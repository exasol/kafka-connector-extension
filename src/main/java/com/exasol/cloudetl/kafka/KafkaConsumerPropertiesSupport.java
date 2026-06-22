package com.exasol.cloudetl.kafka;

import java.util.Map;

import com.exasol.ExaMetadata;

public final class KafkaConsumerPropertiesSupport {
    public static final String INNER_PROPERTY_SEPARATOR = KafkaConsumerProperties.INNER_PROPERTY_SEPARATOR;
    public static final String INNER_KEYVALUE_ASSIGNMENT = KafkaConsumerProperties.INNER_KEYVALUE_ASSIGNMENT;
    public static final KafkaConsumerProperties.Config<String> BOOTSTRAP_SERVERS = KafkaConsumerProperties.BOOTSTRAP_SERVERS;
    public static final KafkaConsumerProperties.Config<String> SCHEMA_REGISTRY_URL = KafkaConsumerProperties.SCHEMA_REGISTRY_URL;
    public static final KafkaConsumerProperties.Config<String> SECURITY_PROTOCOL = KafkaConsumerProperties.SECURITY_PROTOCOL;
    public static final KafkaConsumerProperties.Config<String> SSL_KEY_PASSWORD = KafkaConsumerProperties.SSL_KEY_PASSWORD;
    public static final KafkaConsumerProperties.Config<String> SSL_KEYSTORE_PASSWORD = KafkaConsumerProperties.SSL_KEYSTORE_PASSWORD;
    public static final KafkaConsumerProperties.Config<String> SSL_KEYSTORE_LOCATION = KafkaConsumerProperties.SSL_KEYSTORE_LOCATION;
    public static final KafkaConsumerProperties.Config<String> SSL_TRUSTSTORE_PASSWORD = KafkaConsumerProperties.SSL_TRUSTSTORE_PASSWORD;
    public static final KafkaConsumerProperties.Config<String> SSL_TRUSTSTORE_LOCATION = KafkaConsumerProperties.SSL_TRUSTSTORE_LOCATION;
    public static final KafkaConsumerProperties.Config<String> ENABLE_AUTO_COMMIT = KafkaConsumerProperties.ENABLE_AUTO_COMMIT;
    public static final KafkaConsumerProperties.Config<String> GROUP_ID = KafkaConsumerProperties.GROUP_ID;
    public static final KafkaConsumerProperties.Config<String> MAX_POLL_RECORDS = KafkaConsumerProperties.MAX_POLL_RECORDS;
    public static final KafkaConsumerProperties.Config<String> FETCH_MIN_BYTES = KafkaConsumerProperties.FETCH_MIN_BYTES;
    public static final KafkaConsumerProperties.Config<String> FETCH_MAX_BYTES = KafkaConsumerProperties.FETCH_MAX_BYTES;
    public static final KafkaConsumerProperties.Config<String> MAX_PARTITION_FETCH_BYTES = KafkaConsumerProperties.MAX_PARTITION_FETCH_BYTES;
    public static final KafkaConsumerProperties.Config<String> SASL_KRB5CONF_LOCATION = KafkaConsumerProperties.SASL_KRB5CONF_LOCATION;

    private KafkaConsumerPropertiesSupport() {
    }

    public static KafkaConsumerProperties create(final Map<String, String> params) {
        return KafkaConsumerProperties.apply(params);
    }

    public static KafkaConsumerProperties create(final Map<String, String> params,
            final ExaMetadata metadata) {
        return KafkaConsumerProperties.apply(params, metadata);
    }

    public static KafkaConsumerProperties create(final String string) {
        return KafkaConsumerProperties.apply(string);
    }

    public static KafkaConsumerProperties create(final String string, final ExaMetadata metadata) {
        return KafkaConsumerProperties.apply(string, metadata);
    }
}
