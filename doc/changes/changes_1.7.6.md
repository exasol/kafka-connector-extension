# Kafka Connector Extension 1.7.6, released 2024-07-04

Code name: Fix CVE-2021-47621 and update dependencies

## Summary

Fixes CVE-2021-47621 and updates dependencies.

## Security

* #98: CVE-2021-47621: io.github.classgraph:classgraph:jar:4.8.21:test

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.17.1`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.2.1-jre`
* Updated `io.confluent:kafka-avro-serializer:7.6.0` to `7.6.1`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.26.2`

#### Test Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.3` to `1.5.6`
* Updated `ch.qos.logback:logback-core:1.5.3` to `1.5.6`
* Updated `com.exasol:exasol-testcontainers:7.0.1` to `7.1.0`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.12`
* Updated `io.confluent:kafka-streams-avro-serde:7.6.0` to `7.6.1`
* Added `io.github.classgraph:classgraph:4.8.174`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.6.0` to `7.6.1.1`
* Updated `org.testcontainers:kafka:1.19.7` to `1.19.8`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.2` to `2.0.3`
* Updated `com.exasol:project-keeper-maven-plugin:4.3.0` to `4.3.3`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.3.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0` to `3.2.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.11.0.3922` to `4.0.0.4121`
