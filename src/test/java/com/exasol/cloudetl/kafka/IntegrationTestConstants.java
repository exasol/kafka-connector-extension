package com.exasol.cloudetl.kafka;

import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

public class IntegrationTestConstants {
    public static final String PROJECT_VERSION = MavenProjectVersionGetter.getCurrentProjectVersion();
    public static String JAR_FILE_NAME = "exasol-kafka-connector-extension-" + PROJECT_VERSION + ".jar";
    public static String TEST_SCHEMA_NAME = "kafka_schema";
    public static String DEFAULT_EXASOL_DOCKER_IMAGE = "7.1.24";
    public static String LOCALSTACK_DOCKER_IMAGE = "localstack/localstack:2.2";
    public static String DOCKER_IP_ADDRESS = "172.17.0.1";
}
