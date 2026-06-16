package com.exasol.cloudetl.kafka;

import static com.exasol.cloudetl.kafka.KafkaConsumerProperties.*;
import static com.exasol.cloudetl.kafka.TestCollections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.net.URISyntaxException;
import java.nio.file.*;
import java.util.*;

import org.junit.jupiter.api.*;

import com.exasol.*;

import nl.jqno.equalsverifier.EqualsVerifier;

class KafkaConsumerPropertiesTest {
    private Map<String, String> properties;

    private final Path dummyKeystoreFile = resourcePath("/kafka.consumer.keystore.jks");
    private final Path dummyTruststoreFile = resourcePath("/kafka.consumer.truststore.jks");
    private final Path dummySaslJaasFile = resourcePath("/kafka_client_jaas.conf");
    private final Path dummyKrb5ConfFile = resourcePath("/test_krb5.conf");

    @BeforeEach
    void beforeEach() {
        this.properties = new LinkedHashMap<>();
    }

    @Test
    void configEqualsAndHashCodeFollowContract() {
        EqualsVerifier.forClass(Config.class).verify();
    }

    @Test
    void consumerPropertiesCanEncodeAndDecodePropertyWithEmptyValue() {
        this.properties = mutableMap("BOOTSTRAP_SERVERS", "kafka01", "SSL_ENDPOINT_IDENTIFICATION_ALGORITHM", "",
                "GROUP_ID", "");

        final var consumer = KafkaConsumerPropertiesSupport.create(KafkaConsumerPropertiesSupport
                .create(ScalaCollections.immutableMap(this.properties)).mkString());

        assertEquals("", consumer.getSSLEndpointIdentificationAlgorithm());
        assertEquals("", consumer.getGroupId());
    }

    @Test
    void requiredAndDefaultBaseProperties() {
        assertGetter("BOOTSTRAP_SERVERS", "kafka01.example.com,kafka02.example.com",
                () -> base().getBootstrapServers());
        expectMissingProperty("BOOTSTRAP_SERVERS", () -> base().getBootstrapServers());
        assertGetter("GROUP_ID", "groupId", () -> base().getGroupId());
        this.properties.clear();
        assertEquals("EXASOL_KAFKA_UDFS_CONSUMERS", base().getGroupId());
        assertGetter("TOPIC_NAME", "Metamorphosis", () -> base().getTopic());
        expectMissingProperty("TOPIC_NAME", () -> base().getTopic());
        assertGetter("TABLE_NAME", "table", () -> base().getTableName());
        expectMissingProperty("TABLE_NAME", () -> base().getTableName());
    }

    @Test
    void numericProperties() {
        assertGetter("POLL_TIMEOUT_MS", "10", () -> base().getPollTimeoutMs(), 10L);
        this.properties.clear();
        assertEquals(30000L, base().getPollTimeoutMs());
        this.properties.put("POLL_TIMEOUT_MS", "1l");
        assertThrows(NumberFormatException.class, () -> base().getPollTimeoutMs());

        this.properties.clear();
        assertGetter("MIN_RECORDS_PER_RUN", "7", () -> base().getMinRecordsPerRun(), 7);
        this.properties.clear();
        assertEquals(100, base().getMinRecordsPerRun());
        this.properties.put("MIN_RECORDS_PER_RUN", "e");
        assertThrows(NumberFormatException.class, () -> base().getMinRecordsPerRun());

        this.properties.clear();
        assertGetter("MAX_RECORDS_PER_RUN", "43", () -> base().getMaxRecordsPerRun(), 43);
        this.properties.clear();
        assertEquals(1000000, base().getMaxRecordsPerRun());
        this.properties.put("MAX_RECORDS_PER_RUN", "max");
        assertThrows(NumberFormatException.class, () -> base().getMaxRecordsPerRun());
    }

    @Test
    void securityProtocolFlags() {
        assertFalse(base().isSSLEnabled());
        assertFalse(base().isSASLEnabled());

        this.properties.put("SECURITY_PROTOCOL", "SSL");
        assertTrue(base().isSSLEnabled());
        assertFalse(base().isSASLEnabled());

        this.properties.put("SECURITY_PROTOCOL", "SASL_PLAINTEXT");
        assertFalse(base().isSSLEnabled());
        assertTrue(base().isSASLEnabled());

        this.properties.put("SECURITY_PROTOCOL", "SASL_SSL");
        assertFalse(base().isSSLEnabled());
        assertTrue(base().isSASLEnabled());
    }

    @Test
    void schemaRegistryProperties() {
        assertFalse(base().hasSchemaRegistryUrl());
        assertGetter("SCHEMA_REGISTRY_URL", "http://a-schema.url", () -> base().getSchemaRegistryUrl());
        assertTrue(base().hasSchemaRegistryUrl());

        this.properties.clear();
        expectMissingProperty("SCHEMA_REGISTRY_URL", () -> base().getSchemaRegistryUrl());

        final var avroProperties = map(entry("BOOTSTRAP_SERVERS", "server"), entry("RECORD_FORMAT", "avro"));
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> KafkaConsumerPropertiesSupport.create(avroProperties).getProperties());
        assertTrue(thrown.getMessage().contains(errorMessage("SCHEMA_REGISTRY_URL")));
    }

    @Test
    void kafkaConsumerDefaultsAndOverrides() {
        assertGetter("MAX_POLL_RECORDS", "9", () -> base().getMaxPollRecords());
        assertDefault("500", () -> base().getMaxPollRecords());
        assertGetter("FETCH_MIN_BYTES", "23", () -> base().getFetchMinBytes());
        assertDefault("1", () -> base().getFetchMinBytes());
        assertGetter("FETCH_MAX_BYTES", "27", () -> base().getFetchMaxBytes());
        assertDefault("52428800", () -> base().getFetchMaxBytes());
        assertGetter("MAX_PARTITION_FETCH_BYTES", "4", () -> base().getMaxPartitionFetchBytes());
        assertDefault("1048576", () -> base().getMaxPartitionFetchBytes());
        assertDefault("earliest", () -> base().getAutoOffsetReset());
        assertGetter("SECURITY_PROTOCOL", "SSL", () -> base().getSecurityProtocol());
        assertDefault("PLAINTEXT", () -> base().getSecurityProtocol());
    }

    @Test
    void sslProperties() {
        assertGetter("SSL_KEY_PASSWORD", "1337", () -> base().getSSLKeyPassword());
        expectMissingProperty("SSL_KEY_PASSWORD", () -> base().getSSLKeyPassword());
        assertGetter("SSL_KEYSTORE_PASSWORD", "p@ss", () -> base().getSSLKeystorePassword());
        expectMissingProperty("SSL_KEYSTORE_PASSWORD", () -> base().getSSLKeystorePassword());
        assertGetter("SSL_KEYSTORE_LOCATION", "/path/keystore.jks", () -> base().getSSLKeystoreLocation());
        expectMissingProperty("SSL_KEYSTORE_LOCATION", () -> base().getSSLKeystoreLocation());
        assertGetter("SSL_TRUSTSTORE_PASSWORD", "tp@ss", () -> base().getSSLTruststorePassword());
        expectMissingProperty("SSL_TRUSTSTORE_PASSWORD", () -> base().getSSLTruststorePassword());
        assertGetter("SSL_TRUSTSTORE_LOCATION", "/path/truststore.jks", () -> base().getSSLTruststoreLocation());
        expectMissingProperty("SSL_TRUSTSTORE_LOCATION", () -> base().getSSLTruststoreLocation());
        assertGetter("SSL_ENDPOINT_IDENTIFICATION_ALGORITHM", "none", () -> base().getSSLEndpointIdentificationAlgorithm());
        assertDefault("https", () -> base().getSSLEndpointIdentificationAlgorithm());
    }

    @Test
    void saslJaasConfigCanComeFromCredentialsOrFile() {
        this.properties = mutableMap("SASL_MECHANISM", "PLAIN", "SASL_USERNAME", "kafka", "SASL_PASSWORD", "kafkapw");
        assertEquals("org.apache.kafka.common.security.plain.PlainLoginModule required "
                + "username=\"kafka\" password=\"kafkapw\";", base().getSASLJaasConfig());

        this.properties = mutableMap("SASL_JAAS_LOCATION", this.dummySaslJaasFile.toString());
        assertEquals("org.apache.kafka.common.security.oauthbearer.OAuthBearerLoginModule required "
                + "unsecuredLoginStringClaim_sub=\"alice\";", base().getSASLJaasConfig());
    }

    @Test
    void consumeAllOffsetsAndRecordFormats() {
        this.properties.put("CONSUME_ALL_OFFSETS", "true");
        assertTrue(base().isConsumeAllOffsetsEnabled());
        this.properties.clear();
        assertFalse(base().isConsumeAllOffsetsEnabled());

        assertGetter("RECORD_KEY_FORMAT", "keyFormat", () -> base().getRecordKeyFormat(), "keyformat");
        assertDefault("string", () -> base().getRecordKeyFormat());

        final Map<String, String> values = Map.of("avro", "avro", "JsOn", "json", "String", "string");
        for (final Map.Entry<String, String> entry : values.entrySet()) {
            this.properties = mutableMap("RECORD_VALUE_FORMAT", entry.getKey());
            assertEquals(entry.getValue(), base().getRecordValueFormat());
        }
        this.properties = mutableMap("RECORD_FORMAT", "new_FormaT");
        assertEquals("new_format", base().getRecordValueFormat());
        this.properties.clear();
        assertEquals("avro", base().getRecordValueFormat());
    }

    @Test
    void recordFields() {
        this.properties = mutableMap("RECORD_FIELDS", "value.name,value.Sur_Name, value.address1, timestamp");
        assertSeqEquals(Arrays.asList("value.name", "value.Sur_Name", "value.address1", "timestamp"),
                base().getRecordFields());
        this.properties.clear();
        assertSeqEquals(List.of("value.*"), base().getRecordFields());
        this.properties = mutableMap("RECORD_VALUE_FORMAT", "json");
        assertSeqEquals(List.of("value"), base().getRecordFields());
        this.properties = mutableMap("AS_JSON_DOC", "true");
        assertSeqEquals(List.of("value"), base().getRecordFields());
    }

    @Test
    void getPropertiesReturnsJavaMapProperties() {
        final Map<Config<String>, String> requiredProperties = new LinkedHashMap<>();
        requiredProperties.put(BOOTSTRAP_SERVERS, "kafka.broker.com:9092");
        requiredProperties.put(SCHEMA_REGISTRY_URL, "http://schema-registry.com:8080");
        requiredProperties.put(SECURITY_PROTOCOL, "SSL");
        requiredProperties.put(SSL_KEY_PASSWORD, "sslKeyPass");
        requiredProperties.put(SSL_KEYSTORE_PASSWORD, "sslKeystorePass");
        requiredProperties.put(SSL_KEYSTORE_LOCATION, "/bucket/keystore.JKS");
        requiredProperties.put(SSL_TRUSTSTORE_PASSWORD, "sslTruststorePass");
        requiredProperties.put(SSL_TRUSTSTORE_LOCATION, "/bucket/truststore.JKS");

        final Map<Config<String>, String> optionalProperties = Map.of(
                ENABLE_AUTO_COMMIT, "false",
                GROUP_ID, "EXASOL_KAFKA_UDFS_CONSUMERS",
                MAX_POLL_RECORDS, "500",
                FETCH_MIN_BYTES, "1",
                FETCH_MAX_BYTES, "52428800",
                MAX_PARTITION_FETCH_BYTES, "1048576");

        this.properties = mutableMap("SSL_ENABLED", "true");
        requiredProperties.forEach((key, value) -> this.properties.put(key.userPropertyName(), value));
        final Map<String, Object> javaProps = base().getProperties();

        requiredProperties.forEach((key, value) -> assertEquals(value, javaProps.get(key.kafkaPropertyName())));
        optionalProperties.forEach((key, value) -> assertEquals(value, javaProps.get(key.kafkaPropertyName())));
    }

    @Test
    void mergeWithConnectionObjectReturnsNewKafkaConsumerProperties() throws Exception {
        final var kafkaConsumerProperties = new BaseProperties(map(entry("TOPICS", "test-topic"),
                entry("CONNECTION_NAME", "MY_CONNECTION")));
        final ExaMetadata metadata = mock(ExaMetadata.class);
        final ExaConnectionInformation connectionInformation = mock(ExaConnectionInformation.class);
        when(metadata.getConnection("MY_CONNECTION")).thenReturn(connectionInformation);
        when(connectionInformation.getUser()).thenReturn("");
        when(connectionInformation.getPassword()).thenReturn("BOOTSTRAP_SERVERS=MY_BOOTSTRAP_SERVERS;"
                + "SCHEMA_REGISTRY_URL=MY_SCHEMA_REGISTRY;SECURITY_PROTOCOL=SSL;"
                + "SSL_KEYSTORE_LOCATION=MY_KEYSTORE_LOCATION;SSL_KEYSTORE_PASSWORD=MY_KEYSTORE_PASSWORD;"
                + "SSL_KEY_PASSWORD=MY_SSL_KEY_PASSWORD;SSL_TRUSTSTORE_LOCATION=MY_TRUSTSTORE_LOCATION;"
                + "SSL_TRUSTSTORE_PASSWORD=MY_TRUSTSTORE_PASSWORD");

        final KafkaConsumerProperties mergedKafkaConsumerProperties = kafkaConsumerProperties.mergeWithConnectionObject(metadata);

        assertEquals("BOOTSTRAP_SERVERS -> MY_BOOTSTRAP_SERVERS;CONNECTION_NAME -> MY_CONNECTION;"
                + "SCHEMA_REGISTRY_URL -> MY_SCHEMA_REGISTRY;SECURITY_PROTOCOL -> SSL;"
                + "SSL_KEYSTORE_LOCATION -> MY_KEYSTORE_LOCATION;SSL_KEYSTORE_PASSWORD -> MY_KEYSTORE_PASSWORD;"
                + "SSL_KEY_PASSWORD -> MY_SSL_KEY_PASSWORD;SSL_TRUSTSTORE_LOCATION -> MY_TRUSTSTORE_LOCATION;"
                + "SSL_TRUSTSTORE_PASSWORD -> MY_TRUSTSTORE_PASSWORD;TOPICS -> test-topic",
                mergedKafkaConsumerProperties.mkString());
    }

    @Test
    void applyRejectsSecureSslPropertiesWithoutConnectionObject() {
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> KafkaConsumerPropertiesSupport.create(map(entry("SECURITY_PROTOCOL", "SSL"),
                        entry("SSL_KEY_PASSWORD", "PASSWORD")), mock(ExaMetadata.class)));

        assertTrue(thrown.getMessage().contains("Please use a named Exasol connection object to provide secure properties."));
    }

    @Test
    void applyObtainsBootstrapServersAndSchemaRegistryUrlFromConnectionObject() throws Exception {
        final ExaMetadata metadata = mock(ExaMetadata.class);
        final ExaConnectionInformation connectionInformation = mock(ExaConnectionInformation.class);
        when(metadata.getConnection("MY_CONNECTION")).thenReturn(connectionInformation);
        when(connectionInformation.getUser()).thenReturn("");
        when(connectionInformation.getPassword()).thenReturn("BOOTSTRAP_SERVERS=localhost:1000;"
                + "SCHEMA_REGISTRY_URL=http://n11:1001");

        final var props = KafkaConsumerPropertiesSupport.create(map(entry("CONNECTION_NAME", "MY_CONNECTION")), metadata);

        assertEquals("localhost:1000", props.getBootstrapServers());
        assertEquals("http://n11:1001", props.getSchemaRegistryUrl());
    }

    @Test
    void sslSecurityValidation() throws Exception {
        final var props = getSecurityEnabledConsumerProperties("SSL", this.dummyKeystoreFile, this.dummyTruststoreFile, null);
        assertEquals(this.dummyKeystoreFile.toString(), props.getSSLKeystoreLocation());
        assertEquals(this.dummyTruststoreFile.toString(), props.getSSLTruststoreLocation());

        assertMissingSecurityFile("SSL", Paths.get("ssl_keystore_file"), this.dummyTruststoreFile, null,
                "Unable to find the SSL keystore file");
        assertMissingSecurityFile("SSL", this.dummyKeystoreFile, Paths.get("ssl_truststore_file"), null,
                "Unable to find the SSL truststore file");
    }

    @Test
    void saslSslSecurityValidationAndProperties() throws Exception {
        assertEquals("PLAIN", getSecurityEnabledConsumerProperties("SASL_SSL", null, null, null).getSASLMechanism());

        final var props = getSecurityEnabledConsumerProperties("SASL_SSL", this.dummyKeystoreFile,
                this.dummyTruststoreFile, null);
        assertTrue(props.getSASLJaasConfig().contains("\"pass\""));
        assertEquals(this.dummyKeystoreFile.toString(), props.getSSLKeystoreLocation());
        assertEquals(this.dummyTruststoreFile.toString(), props.getSSLTruststoreLocation());

        assertMissingSecurityFile("SASL_SSL", Paths.get("ssl_keystore_file"), null, null,
                "Unable to find the SSL keystore file");
        assertMissingSecurityFile("SASL_SSL", this.dummyKeystoreFile, Paths.get("ssl_truststore_file"), null,
                "Unable to find the SSL truststore file");

        assertEquals(this.dummyKeystoreFile.toString(),
                getSecurityEnabledConsumerProperties("SASL_SSL", this.dummyKeystoreFile, null, null).getProperties()
                        .get(SSL_KEYSTORE_LOCATION.kafkaPropertyName()));
        assertEquals(this.dummyTruststoreFile.toString(),
                getSecurityEnabledConsumerProperties("SASL_SSL", null, this.dummyTruststoreFile, null).getProperties()
                        .get(SSL_TRUSTSTORE_LOCATION.kafkaPropertyName()));
        assertEquals("kpw", getSecurityEnabledConsumerProperties("SASL_SSL", null, null, null).getProperties()
                .get(SSL_KEY_PASSWORD.kafkaPropertyName()));
    }

    @Test
    void krb5ConfValidation() throws Exception {
        final var properties = getSecurityEnabledConsumerProperties("SASL_SSL", null, null, Paths.get("krb5_non_existing"));

        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class, () -> properties.getProperties());

        assertAll(() -> assertTrue(thrown.getMessage().contains("Unable to find the custom krb5.conf file")),
                () -> assertTrue(thrown.getMessage().contains("Please make sure it is successfully uploaded to BucketFS bucket")));

        final var props = getSecurityEnabledConsumerProperties("SASL_SSL", null, null, this.dummyKrb5ConfFile).getProperties();
        assertEquals(this.dummyKrb5ConfFile.toString(), props.get(SASL_KRB5CONF_LOCATION.kafkaPropertyName()));
    }

    private void assertMissingSecurityFile(final String protocol, final Path keyStore, final Path trustStore,
            final Path krb5Conf, final String expectedMessage) {
        final KafkaConnectorException thrown = assertThrows(KafkaConnectorException.class,
                () -> getSecurityEnabledConsumerProperties(protocol, keyStore, trustStore, krb5Conf));
        assertAll(() -> assertTrue(thrown.getMessage().contains(expectedMessage)),
                () -> assertTrue(thrown.getMessage().contains("Please make sure it is successfully uploaded to BucketFS bucket")));
    }

    private KafkaConsumerProperties getSecurityEnabledConsumerProperties(final String securityProtocol,
            final Path keystoreFile, final Path truststoreFile, final Path krb5confFile) throws Exception {
        final ExaMetadata metadata = mock(ExaMetadata.class);
        final ExaConnectionInformation connectionInformation = mock(ExaConnectionInformation.class);
        when(metadata.getConnection("SSL_CONNECTION")).thenReturn(connectionInformation);
        when(connectionInformation.getUser()).thenReturn("");
        final StringBuilder builder = new StringBuilder();
        appendPath(builder, "SSL_KEYSTORE_LOCATION", keystoreFile);
        appendPath(builder, "SSL_TRUSTSTORE_LOCATION", truststoreFile);
        appendPath(builder, "SASL_KRB5CONF_LOCATION", krb5confFile);
        if ("SSL".equals(securityProtocol)) {
            addSimpleSslParameters(builder);
        } else if ("SASL_SSL".equals(securityProtocol)) {
            addSimpleSslParameters(builder);
            builder.append(";");
            addSimpleSaslParameters(builder);
        }
        when(connectionInformation.getPassword()).thenReturn(builder.toString());
        return KafkaConsumerPropertiesSupport.create(map(entry("BOOTSTRAP_SERVERS", "kafka01"),
                entry("RECORD_FORMAT", "string"), entry("SECURITY_PROTOCOL", securityProtocol),
                entry("CONNECTION_NAME", "SSL_CONNECTION")), metadata);
    }

    private void appendPath(final StringBuilder builder, final String key, final Path file) {
        if (file != null) {
            builder.append(key).append("=").append(file).append(";");
        }
    }

    private void addSimpleSslParameters(final StringBuilder builder) {
        builder.append("SSL_KEY_PASSWORD=kpw;SSL_KEYSTORE_PASSWORD=kspw;SSL_TRUSTSTORE_PASSWORD=tspw");
    }

    private void addSimpleSaslParameters(final StringBuilder builder) {
        builder.append("SASL_MECHANISM=PLAIN;SASL_USERNAME=user;SASL_PASSWORD=pass");
    }

    private BaseProperties base() {
        return new BaseProperties(ScalaCollections.immutableMap(this.properties));
    }

    private void assertGetter(final String key, final String value, final StringSupplier getter) {
        assertGetter(key, value, getter, value);
    }

    private <T> void assertGetter(final String key, final String value, final SupplierWithException<T> getter,
            final T expected) {
        this.properties.clear();
        this.properties.put(key, value);
        assertEquals(expected, getter.get());
    }

    private void assertDefault(final String expected, final StringSupplier getter) {
        this.properties.clear();
        assertEquals(expected, getter.get());
    }

    private void expectMissingProperty(final String key, final Runnable getter) {
        this.properties.clear();
        final IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, getter::run);
        assertTrue(thrown.getMessage().contains(errorMessage(key)));
    }

    private String errorMessage(final String key) {
        return "Please provide key-value pairs for '" + key + "' property.";
    }

    private Path resourcePath(final String name) {
        try {
            return Paths.get(getClass().getResource(name).toURI()).toAbsolutePath();
        } catch (final URISyntaxException exception) {
            throw new IllegalStateException("Invalid test resource URI " + name, exception);
        }
    }

    private Map<String, String> mutableMap(final String... values) {
        final Map<String, String> result = new LinkedHashMap<>();
        for (int index = 0; index < values.length; index += 2) {
            result.put(values[index], values[index + 1]);
        }
        return result;
    }

    @FunctionalInterface
    private interface StringSupplier extends SupplierWithException<String> {
    }

    @FunctionalInterface
    private interface SupplierWithException<T> {
        T get();
    }

    private static final class BaseProperties extends KafkaConsumerProperties {
        private BaseProperties(final scala.collection.immutable.Map<String, String> params) {
            super(params);
        }
    }
}
