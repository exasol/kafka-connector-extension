# Exasol Kafka Connector Extension 1.7.4, released 2024-03-14

Code name: Fix CVE-2024-25710 in compile dependency

## Summary

This release fixes the following vulnerabilities in dependencies:
* CVE-2024-25710 in `org.apache.commons:commons-compress:jar:1.21:compile`
* CVE-2024-22201 in `org.eclipse.jetty.http2:http2-common:jar:9.4.53.v20231009:test`
* CVE-2023-51775 in `org.bitbucket.b_c:jose4j:jar:0.9.3:test`

## Security

* #88: Fixed CVE-2024-25710 in `org.apache.commons:commons-compress:jar:1.21:compile`
* #89: Fixed CVE-2024-22201 in `org.eclipse.jetty.http2:http2-common:jar:9.4.53.v20231009:test`
* #90: Fixed CVE-2023-51775 in `org.bitbucket.b_c:jose4j:jar:0.9.3:test`

## Dependency Updates

### Compile Dependency Updates

* Updated `com.exasol:import-export-udf-common-scala_2.13:1.1.1` to `2.0.0`
* Updated `com.google.guava:guava:33.0.0-jre` to `33.1.0-jre`
* Updated `io.confluent:kafka-avro-serializer:7.5.2` to `7.6.0`
* Added `org.apache.avro:avro:1.11.3`
* Updated `org.apache.commons:commons-compress:1.26.0` to `1.26.1`
* Updated `org.apache.kafka:kafka-clients:3.5.1` to `7.6.0-ce`

### Test Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.4.14` to `1.5.3`
* Updated `ch.qos.logback:logback-core:1.4.14` to `1.5.3`
* Updated `com.exasol:exasol-testcontainers:7.0.0` to `7.0.1`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.7` to `0.5.8`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.3` to `1.6.5`
* Updated `com.exasol:test-db-builder-java:3.5.3` to `3.5.4`
* Updated `com.sksamuel.avro4s:avro4s-core_2.13:4.1.1` to `4.1.2`
* Updated `io.confluent:kafka-streams-avro-serde:7.5.2` to `7.6.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.5.2` to `7.6.0`
* Removed `org.apache.avro:avro:1.11.3`
* Updated `org.apache.zookeeper:zookeeper:3.9.1` to `3.9.2`
* Added `org.bitbucket.b_c:jose4j:0.9.6`
* Added `org.eclipse.jetty.http2:http2-common:11.0.20`
* Updated `org.json:json:20231013` to `20240303`
* Updated `org.mockito:mockito-core:5.8.0` to `5.11.0`
* Updated `org.testcontainers:kafka:1.19.3` to `1.19.7`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:1.3.1` to `2.0.0`
* Updated `com.exasol:project-keeper-maven-plugin:3.0.1` to `4.1.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.11.0` to `3.12.1`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.3` to `3.2.5`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.6.2` to `3.6.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.3` to `3.2.5`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.5.0` to `1.6.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:1.6.2` to `1.8.0`
