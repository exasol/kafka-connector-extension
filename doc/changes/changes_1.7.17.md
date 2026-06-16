# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Security updates in transient components.

## Summary

This release updates dependencies to fix CVEs in transient components.

## Security

* #154: CVE-2025-12383: org.glassfish.jersey.core:jersey-client:jar:2.45:test

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.34`
* Removed `com.google.code.gson:gson:2.13.1`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.2.0`
* Added `io.confluent:kafka-schema-registry-client:8.2.0`
* Updated `org.apache.kafka:kafka-clients:3.9.1` to `4.2.0`
* Removed `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814`
* Updated `org.slf4j:slf4j-api:2.0.17` to `2.0.18`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.3.0`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.20`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `4.0.1`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.2.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.2.0`
* Added `jakarta.xml.bind:jakarta.xml.bind-api:4.0.5`
* Added `javax.ws.rs:javax.ws.rs-api:2.1.1`
* Removed `org.apache.mina:mina-core:2.2.4`
* Added `org.glassfish.jersey.core:jersey-client:3.1.11`
* Added `org.glassfish.jersey.inject:jersey-hk2:3.1.11`
* Added `org.glassfish.jersey.media:jersey-media-json-jackson:3.1.11`
* Added `org.glassfish.jersey.media:jersey-media-multipart:3.1.11`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.5` to `2.0.7`
* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.6.2`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.2` to `10.0.0`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.7.1` to `3.8.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.1` to `3.15.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.4` to `3.5.5`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.4.2` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.4` to `3.5.5`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.19.1` to `2.21.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.2.0.4988` to `5.5.0.6356`
