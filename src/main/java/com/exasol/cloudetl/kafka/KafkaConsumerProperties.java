package com.exasol.cloudetl.kafka;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SaslConfigs;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.security.auth.SecurityProtocol;

import com.exasol.ExaMetadata;
import com.exasol.common.AbstractProperties;
import com.exasol.common.PropertiesParser;
import com.exasol.errorreporting.ExaError;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import scala.Option;

public class KafkaConsumerProperties extends AbstractProperties {
    public static final String INNER_PROPERTY_SEPARATOR = ";";
    public static final String INNER_KEYVALUE_ASSIGNMENT = " -> ";

    public static final String TOPIC_NAME = "TOPIC_NAME";
    public static final String TABLE_NAME = "TABLE_NAME";
    public static final String SSL_ENABLED = "SSL_ENABLED";
    public static final String AS_JSON_DOC = "AS_JSON_DOC";
    public static final String CONSUME_ALL_OFFSETS = "CONSUME_ALL_OFFSETS";
    public static final String RECORD_FIELDS = "RECORD_FIELDS";
    public static final String RECORD_FORMAT = "RECORD_FORMAT";
    public static final String RECORD_KEY_FORMAT = "RECORD_KEY_FORMAT";
    public static final String RECORD_VALUE_FORMAT = "RECORD_VALUE_FORMAT";

    public static final Config<Long> POLL_TIMEOUT_MS = new Config<>("POLL_TIMEOUT_MS", "", 30000L);
    public static final Config<Integer> MIN_RECORDS_PER_RUN = new Config<>("MIN_RECORDS_PER_RUN", "", 100);
    public static final Config<Integer> MAX_RECORDS_PER_RUN = new Config<>("MAX_RECORDS_PER_RUN", "", 1000000);
    public static final Config<String> ENABLE_AUTO_COMMIT = new Config<>("ENABLE_AUTO_COMMIT", ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
    public static final Config<String> BOOTSTRAP_SERVERS = new Config<>("BOOTSTRAP_SERVERS", ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "");
    public static final Config<String> GROUP_ID = new Config<>("GROUP_ID", ConsumerConfig.GROUP_ID_CONFIG, "EXASOL_KAFKA_UDFS_CONSUMERS");
    public static final Config<String> AUTO_OFFSET_RESET = new Config<>("AUTO_OFFSET_RESET", ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    public static final Config<String> MAX_POLL_RECORDS = new Config<>("MAX_POLL_RECORDS", ConsumerConfig.MAX_POLL_RECORDS_CONFIG, "500");
    public static final Config<String> FETCH_MIN_BYTES = new Config<>("FETCH_MIN_BYTES", ConsumerConfig.FETCH_MIN_BYTES_CONFIG, "1");
    public static final Config<String> FETCH_MAX_BYTES = new Config<>("FETCH_MAX_BYTES", ConsumerConfig.FETCH_MAX_BYTES_CONFIG,
            String.valueOf(ConsumerConfig.DEFAULT_FETCH_MAX_BYTES));
    public static final Config<String> MAX_PARTITION_FETCH_BYTES = new Config<>("MAX_PARTITION_FETCH_BYTES", ConsumerConfig.MAX_PARTITION_FETCH_BYTES_CONFIG,
            String.valueOf(ConsumerConfig.DEFAULT_MAX_PARTITION_FETCH_BYTES));
    public static final Config<String> SCHEMA_REGISTRY_URL = new Config<>("SCHEMA_REGISTRY_URL", AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "");
    public static final Config<String> SECURITY_PROTOCOL = new Config<>("SECURITY_PROTOCOL", CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,
            CommonClientConfigs.DEFAULT_SECURITY_PROTOCOL);
    public static final Config<String> SSL_KEY_PASSWORD = new Config<>("SSL_KEY_PASSWORD", SslConfigs.SSL_KEY_PASSWORD_CONFIG, "");
    public static final Config<String> SSL_KEYSTORE_PASSWORD = new Config<>("SSL_KEYSTORE_PASSWORD", SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "");
    public static final Config<String> SSL_KEYSTORE_LOCATION = new Config<>("SSL_KEYSTORE_LOCATION", SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "");
    public static final Config<String> SSL_TRUSTSTORE_PASSWORD = new Config<>("SSL_TRUSTSTORE_PASSWORD", SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "");
    public static final Config<String> SSL_TRUSTSTORE_LOCATION = new Config<>("SSL_TRUSTSTORE_LOCATION", SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "");
    public static final Config<String> SSL_ENDPOINT_IDENTIFICATION_ALGORITHM = new Config<>("SSL_ENDPOINT_IDENTIFICATION_ALGORITHM",
            SslConfigs.SSL_ENDPOINT_IDENTIFICATION_ALGORITHM_CONFIG, SslConfigs.DEFAULT_SSL_ENDPOINT_IDENTIFICATION_ALGORITHM);
    public static final Config<String> SASL_MECHANISM = new Config<>("SASL_MECHANISM", SaslConfigs.SASL_MECHANISM, SaslConfigs.DEFAULT_SASL_MECHANISM);
    public static final Config<String> SASL_USERNAME = new Config<>("SASL_USERNAME", "", "");
    public static final Config<String> SASL_JAAS_LOCATION = new Config<>("SASL_JAAS_LOCATION", "", "");
    public static final Config<String> SASL_KRB5CONF_LOCATION = new Config<>("SASL_KRB5CONF_LOCATION", "java.security.krb5.conf", "");
    public static final Config<String> SASL_PASSWORD = new Config<>("SASL_PASSWORD", "", "");
    public static final Config<String> SASL_JAAS_CONFIG = new Config<>("SASL_JAAS_CONFIG", SaslConfigs.SASL_JAAS_CONFIG, "");
    public static final String BUCKETFS_CHECK_MITIGATION = "Please make sure it is successfully uploaded to BucketFS bucket.";

    private final Map<String, String> properties;

    public KafkaConsumerProperties(final Map<String, String> properties) {
        super(ScalaCollections.immutableMap(properties));
        this.properties = new LinkedHashMap<>(properties);
    }

    @Deprecated
    public KafkaConsumerProperties(final scala.collection.immutable.Map<String, String> properties) {
        this(ScalaCollections.javaMap(properties));
    }

    public String getBootstrapServers() {
        return getString(BOOTSTRAP_SERVERS.userPropertyName());
    }

    public String getGroupId() {
        return getOrDefault(GROUP_ID);
    }

    public String getAutoOffsetReset() {
        return getOrDefault(AUTO_OFFSET_RESET);
    }

    public boolean isSingleColumnJsonEnabled() {
        return isEnabled(AS_JSON_DOC);
    }

    public String getRecordValueFormat() {
        final String obsoleteFormatValue = getOrDefault(RECORD_FORMAT, "avro");
        return getOrDefault(RECORD_VALUE_FORMAT, obsoleteFormatValue).toLowerCase(Locale.ENGLISH);
    }

    public String getRecordKeyFormat() {
        return getOrDefault(RECORD_KEY_FORMAT, "string").toLowerCase(Locale.ENGLISH);
    }

    public List<String> getRecordFields() {
        final Option<String> fields = get(RECORD_FIELDS);
        if (fields.isDefined()) {
            final java.util.ArrayList<String> result = new java.util.ArrayList<>();
            for (final String field : fields.get().split(",")) {
                result.add(field.trim());
            }
            return result;
        }
        return defaultRecordFields();
    }

    private List<String> defaultRecordFields() {
        final String recordField;
        if (isSingleColumnJsonEnabled()) {
            recordField = "value";
        } else if ("avro".equals(getRecordValueFormat())) {
            recordField = "value.*";
        } else {
            recordField = "value";
        }
        return List.of(recordField);
    }

    public String getTopic() {
        return getString(TOPIC_NAME);
    }

    public String getTableName() {
        return getString(TABLE_NAME);
    }

    public boolean isConsumeAllOffsetsEnabled() {
        return isEnabled(CONSUME_ALL_OFFSETS);
    }

    public long getPollTimeoutMs() {
        return Long.parseLong(getOrDefault(POLL_TIMEOUT_MS.userPropertyName(), String.valueOf(POLL_TIMEOUT_MS.defaultValue())));
    }

    public int getMinRecordsPerRun() {
        return Integer.parseInt(getOrDefault(MIN_RECORDS_PER_RUN.userPropertyName(),
                String.valueOf(MIN_RECORDS_PER_RUN.defaultValue())));
    }

    public int getMaxRecordsPerRun() {
        return Integer.parseInt(getOrDefault(MAX_RECORDS_PER_RUN.userPropertyName(),
                String.valueOf(MAX_RECORDS_PER_RUN.defaultValue())));
    }

    public boolean isSSLEnabled() {
        return KafkaConstants.SSL_PROTOCOLS.contains(SecurityProtocol.valueOf(getSecurityProtocol()));
    }

    public boolean isSASLEnabled() {
        return KafkaConstants.SASL_PROTOCOLS.contains(SecurityProtocol.valueOf(getSecurityProtocol()));
    }

    public boolean hasSchemaRegistryUrl() {
        return containsKey(SCHEMA_REGISTRY_URL.userPropertyName());
    }

    public String getSchemaRegistryUrl() {
        return getString(SCHEMA_REGISTRY_URL.userPropertyName());
    }

    public String getMaxPollRecords() {
        return getOrDefault(MAX_POLL_RECORDS);
    }

    public String getFetchMinBytes() {
        return getOrDefault(FETCH_MIN_BYTES);
    }

    public String getFetchMaxBytes() {
        return getOrDefault(FETCH_MAX_BYTES);
    }

    public String getMaxPartitionFetchBytes() {
        return getOrDefault(MAX_PARTITION_FETCH_BYTES);
    }

    public String getSecurityProtocol() {
        return getOrDefault(SECURITY_PROTOCOL).toUpperCase(Locale.ENGLISH);
    }

    public String getSSLKeyPassword() {
        return getString(SSL_KEY_PASSWORD.userPropertyName());
    }

    public String getSSLKeystorePassword() {
        return getString(SSL_KEYSTORE_PASSWORD.userPropertyName());
    }

    public String getSSLKeystoreLocation() {
        return getString(SSL_KEYSTORE_LOCATION.userPropertyName());
    }

    public String getSSLTruststorePassword() {
        return getString(SSL_TRUSTSTORE_PASSWORD.userPropertyName());
    }

    public String getSSLTruststoreLocation() {
        return getString(SSL_TRUSTSTORE_LOCATION.userPropertyName());
    }

    public String getSSLEndpointIdentificationAlgorithm() {
        return getOrDefault(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM);
    }

    public String getSASLMechanism() {
        return getOrDefault(SASL_MECHANISM);
    }

    public String getSASLJaasLocation() {
        return getOrDefault(SASL_JAAS_LOCATION);
    }

    @SuppressWarnings("java:S2068")
    public String getSASLJaasConfig() {
        final String saslJaasLocation = getSASLJaasLocation();
        if (!saslJaasLocation.isEmpty()) {
            validateSaslJaasLocationFileExist(saslJaasLocation);
            try {
                return Files.readString(Paths.get(saslJaasLocation), StandardCharsets.UTF_8);
            } catch (final IOException exception) {
                throw new KafkaConnectorException("Could not read SASL JAAS file.", exception);
            }
        }
        final String jaasUsername = getString(SASL_USERNAME.userPropertyName());
        final String jaasPassword = getString(SASL_PASSWORD.userPropertyName());
        final String saslModuleName;
        if ("PLAIN".equals(getSASLMechanism())) {
            saslModuleName = "org.apache.kafka.common.security.plain.PlainLoginModule";
        } else if (SecurityProtocol.valueOf(getSecurityProtocol()).name().startsWith("DIGEST")) {
            saslModuleName = "org.apache.zookeeper.server.auth.DigestLoginModule";
        } else if (SecurityProtocol.valueOf(getSecurityProtocol()).name().startsWith("SCRAM")) {
            saslModuleName = "org.apache.kafka.common.security.scram.ScramLoginModule";
        } else {
            throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-9")
                    .message("Could not setup SASL module for secure authentication.")
                    .mitigation("Please use SASL_JAAS_LOCATION for complex configuration of SASL authentication.")
                    .toString());
        }
        return saslModuleName + " required username=\"" + jaasUsername + "\" password=\"" + jaasPassword + "\";";
    }

    public String getSASLKrb5ConfLocation() {
        final Option<String> krb5confLocation = get(SASL_KRB5CONF_LOCATION.userPropertyName());
        if (krb5confLocation.isEmpty()) {
            return "";
        }
        final String path = krb5confLocation.get();
        if (!Files.isRegularFile(Paths.get(path))) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-28")
                    .message("Unable to find the custom krb5.conf file at {{JAAS_LOCATION}}", krb5confLocation)
                    .mitigation(BUCKETFS_CHECK_MITIGATION)
                    .toString());
        }
        return path;
    }

    public Map<String, Object> getProperties() {
        final Map<String, Object> props = new LinkedHashMap<>();
        props.put(ENABLE_AUTO_COMMIT.kafkaPropertyName(), ENABLE_AUTO_COMMIT.defaultValue());
        props.put(BOOTSTRAP_SERVERS.kafkaPropertyName(), getBootstrapServers());
        props.put(GROUP_ID.kafkaPropertyName(), getGroupId());
        props.put(AUTO_OFFSET_RESET.kafkaPropertyName(), getAutoOffsetReset());
        if ("avro".equals(getRecordValueFormat())) {
            props.put(SCHEMA_REGISTRY_URL.kafkaPropertyName(), getSchemaRegistryUrl());
        }
        props.put(MAX_POLL_RECORDS.kafkaPropertyName(), getMaxPollRecords());
        props.put(FETCH_MIN_BYTES.kafkaPropertyName(), getFetchMinBytes());
        props.put(FETCH_MAX_BYTES.kafkaPropertyName(), getFetchMaxBytes());
        props.put(MAX_PARTITION_FETCH_BYTES.kafkaPropertyName(), getMaxPartitionFetchBytes());
        props.put(SECURITY_PROTOCOL.kafkaPropertyName(), getSecurityProtocol());
        if (isSSLEnabled()) {
            props.put(SSL_KEY_PASSWORD.kafkaPropertyName(), getSSLKeyPassword());
            props.put(SSL_KEYSTORE_PASSWORD.kafkaPropertyName(), getSSLKeystorePassword());
            props.put(SSL_KEYSTORE_LOCATION.kafkaPropertyName(), getSSLKeystoreLocation());
            props.put(SSL_TRUSTSTORE_PASSWORD.kafkaPropertyName(), getSSLTruststorePassword());
            props.put(SSL_TRUSTSTORE_LOCATION.kafkaPropertyName(), getSSLTruststoreLocation());
            props.put(SSL_ENDPOINT_IDENTIFICATION_ALGORITHM.kafkaPropertyName(), getSSLEndpointIdentificationAlgorithm());
        }
        if (isSASLEnabled()) {
            props.put(SASL_MECHANISM.kafkaPropertyName(), getSASLMechanism());
            props.put(SASL_JAAS_CONFIG.kafkaPropertyName(), getSASLJaasConfig());
            props.put(SASL_KRB5CONF_LOCATION.kafkaPropertyName(), getSASLKrb5ConfLocation());
            addOptionalParametersForSASL(props);
        }
        return props;
    }

    private void addOptionalParametersForSASL(final Map<String, Object> props) {
        for (final Config<String> config : List.of(SSL_KEY_PASSWORD, SSL_KEYSTORE_PASSWORD, SSL_KEYSTORE_LOCATION,
                SSL_TRUSTSTORE_PASSWORD, SSL_TRUSTSTORE_LOCATION)) {
            final Option<String> value = get(config.userPropertyName());
            if (value.isDefined()) {
                props.put(config.kafkaPropertyName(), value.get());
            }
        }
    }

    public KafkaConsumerProperties mergeWithConnectionObject(final ExaMetadata metadata) {
        final scala.collection.immutable.Map<String, String> connectionParsedMap = parseConnectionInfo(BOOTSTRAP_SERVERS.userPropertyName(),
                Option.apply(metadata));
        final Map<String, String> merged = new LinkedHashMap<>(this.properties);
        merged.putAll(ScalaCollections.javaMap(connectionParsedMap));
        return new KafkaConsumerProperties(merged);
    }

    public String mkString() {
        return mkString(INNER_PROPERTY_SEPARATOR, INNER_KEYVALUE_ASSIGNMENT);
    }

    private String getOrDefault(final Config<String> config) {
        return getOrDefault(config.userPropertyName(), config.defaultValue());
    }

    private String getOrDefault(final String key, final String defaultValue) {
        final Option<String> value = get(key);
        return value.isDefined() ? value.get() : defaultValue;
    }

    private void validateSaslJaasLocationFileExist(final String saslJaasLocation) {
        if (!Files.isRegularFile(Paths.get(saslJaasLocation))) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-5")
                    .message("Unable to find the SASL JAAS file at {{JAAS_LOCATION}}.", saslJaasLocation)
                    .mitigation(BUCKETFS_CHECK_MITIGATION)
                    .toString());
        }
    }

    public static KafkaConsumerProperties apply(final Map<String, String> params) {
        return createConsumerProperties(params, Option.empty());
    }

    @Deprecated
    public static KafkaConsumerProperties apply(final scala.collection.immutable.Map<String, String> params) {
        return apply(ScalaCollections.javaMap(params));
    }

    public static KafkaConsumerProperties apply(final Map<String, String> params,
            final ExaMetadata metadata) {
        return createConsumerProperties(params, Option.apply(metadata));
    }

    @Deprecated
    public static KafkaConsumerProperties apply(final scala.collection.immutable.Map<String, String> params,
            final ExaMetadata metadata) {
        return apply(ScalaCollections.javaMap(params), metadata);
    }

    public static KafkaConsumerProperties apply(final String string) {
        return createConsumerProperties(mapFromString(string), Option.empty());
    }

    public static KafkaConsumerProperties apply(final String string, final ExaMetadata metadata) {
        return createConsumerProperties(mapFromString(string), Option.apply(metadata));
    }

    private static Map<String, String> mapFromString(final String string) {
        return ScalaCollections.javaMap(new PropertiesParser(INNER_PROPERTY_SEPARATOR, INNER_KEYVALUE_ASSIGNMENT).mapFromString(string));
    }

    private static KafkaConsumerProperties createConsumerProperties(
            final Map<String, String> params, final Option<ExaMetadata> metadataOpt) {
        final KafkaConsumerProperties properties = new KafkaConsumerProperties(params);
        validateNoSSLCredentials(properties);
        if (metadataOpt.isEmpty() || !properties.hasNamedConnection()) {
            return properties;
        }
        final KafkaConsumerProperties newProperties = properties.mergeWithConnectionObject(metadataOpt.get());
        validateSSLLocationFilesExist(newProperties);
        return newProperties;
    }

    private static void validateNoSSLCredentials(final KafkaConsumerProperties properties) {
        for (final String property : List.of(SSL_KEYSTORE_LOCATION.userPropertyName(),
                SSL_KEYSTORE_PASSWORD.userPropertyName(), SSL_KEY_PASSWORD.userPropertyName(),
                SSL_TRUSTSTORE_LOCATION.userPropertyName(), SSL_TRUSTSTORE_PASSWORD.userPropertyName(),
                SASL_USERNAME.userPropertyName(), SASL_PASSWORD.userPropertyName())) {
            if (properties.containsKey(property)) {
                throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-6")
                        .message("Providing secure connection values as parameters is not supported.")
                        .mitigation("Please use a named Exasol connection object to provide secure properties.")
                        .toString());
            }
        }
    }

    private static void validateSSLLocationFilesExist(final KafkaConsumerProperties properties) {
        if (properties.isSSLEnabled()) {
            validateKeystoreLocation(properties.getSSLKeystoreLocation());
            validateTrustStoreLocation(properties.getSSLTruststoreLocation());
        } else if (properties.isSASLEnabled()) {
            final Option<String> keyStoreLocation = properties.get(SSL_KEYSTORE_LOCATION.userPropertyName());
            if (keyStoreLocation.isDefined()) {
                validateKeystoreLocation(keyStoreLocation.get());
            }
            final Option<String> trustStoreLocation = properties.get(SSL_TRUSTSTORE_LOCATION.userPropertyName());
            if (trustStoreLocation.isDefined()) {
                validateTrustStoreLocation(trustStoreLocation.get());
            }
        }
    }

    private static void validateKeystoreLocation(final String path) {
        if (!Files.isRegularFile(Paths.get(path))) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-7")
                    .message("Unable to find the SSL keystore file at {{LOCATION}}.", path)
                    .mitigation(BUCKETFS_CHECK_MITIGATION)
                    .toString());
        }
    }

    private static void validateTrustStoreLocation(final String path) {
        if (!Files.isRegularFile(Paths.get(path))) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-8")
                    .message("Unable to find the SSL truststore file at {{LOCATION}}.", path)
                    .mitigation(BUCKETFS_CHECK_MITIGATION)
                    .toString());
        }
    }

    public static final class Config<T> {
        private final String userPropertyName;
        private final String kafkaPropertyName;
        private final T defaultValue;

        public Config(final String userPropertyName, final String kafkaPropertyName, final T defaultValue) {
            this.userPropertyName = userPropertyName;
            this.kafkaPropertyName = kafkaPropertyName;
            this.defaultValue = defaultValue;
        }

        public String userPropertyName() {
            return this.userPropertyName;
        }

        public String kafkaPropertyName() {
            return this.kafkaPropertyName;
        }

        public T defaultValue() {
            return this.defaultValue;
        }

        @Override
        public boolean equals(final Object other) {
            if (!(other instanceof Config<?>)) {
                return false;
            }
            final Config<?> that = (Config<?>) other;
            return Objects.equals(this.userPropertyName, that.userPropertyName)
                    && Objects.equals(this.kafkaPropertyName, that.kafkaPropertyName)
                    && Objects.equals(this.defaultValue, that.defaultValue);
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.userPropertyName, this.kafkaPropertyName, this.defaultValue);
        }

        @Override
        public String toString() {
            return "Config(" + this.userPropertyName + "," + this.kafkaPropertyName + "," + this.defaultValue + ")";
        }
    }
}
