package com.exasol.cloudetl.kafka;

import com.exasol.mavenprojectversiongetter.MavenProjectVersionGetter;

public class IntegrationTestConstants {
    public static final String PROJECT_VERSION = MavenProjectVersionGetter.getCurrentProjectVersion();
    public static final String JAR_FILE_NAME = "exasol-kafka-connector-extension-" + PROJECT_VERSION + ".jar";
    public static final String TEST_SCHEMA_NAME = "kafka_schema";
    public static final String LOCALSTACK_DOCKER_IMAGE = "localstack/localstack:3.8";
    public static final String DOCKER_IP_ADDRESS = "172.17.0.1";
}
