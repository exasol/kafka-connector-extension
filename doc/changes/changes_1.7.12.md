# Kafka Connector Extension 1.7.12, released 2025-04-08

Code name: Update libraries

## Summary

This release updates several dependencies to fix CVE-2024-56128, CVE-2024-55551 and CVE-2024-58103 in test dependencies. 
It also updates kafka client libraries to version 3.9 

## Refactoring

* #103: Upgrade to recent dependencies breaks tests

## Security

* #135 Fix CVE-2024-56128 in org.apache.kafka:kafka_2.13:jar:3.7.1:test
* #133 Fix CVE-2024-55551 in com.exasol:exasol-jdbc:jar:24.1.1:test
* #131 Fix CVE-2024-58103 in com.squareup.wire:wire-runtime-jvm:jar:4.9.7:test

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.16` to `1.5.18`
* Removed `com.fasterxml.jackson.core:jackson-core:2.18.2`
* Removed `com.google.guava:guava:33.4.0-jre`
* Updated `io.confluent:kafka-avro-serializer:7.7.1` to `7.9.0`
* Removed `org.apache.avro:avro:1.12.0`
* Removed `org.apache.commons:commons-compress:1.27.1`
* Updated `org.apache.kafka:kafka-clients:3.7.2` to `3.9.0`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.12.0` to `2.13.0`
* Updated `org.scala-lang:scala-library:2.13.15` to `2.13.16`
* Updated `org.slf4j:slf4j-api:2.0.16` to `2.0.17`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.2` to `7.1.4`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.13` to `0.5.16`
* Removed `com.google.protobuf:protobuf-java:4.29.3`
* Updated `io.confluent:kafka-streams-avro-serde:7.7.1` to `7.9.0`
* Removed `io.github.classgraph:classgraph:4.8.179`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.7.2` to `7.9.0`
* Removed `org.hibernate.validator:hibernate-validator:6.2.5.Final`
* Updated `org.mockito:mockito-core:5.14.2` to `5.17.0`
* Updated `org.testcontainers:kafka:1.20.3` to `1.20.6`

#### Plugin Dependency Updates

* Updated `com.diffplug.spotless:spotless-maven-plugin:2.43.0` to `2.44.4`
* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.4.2` to `0.4.3`
* Updated `com.exasol:project-keeper-maven-plugin:4.5.0` to `5.0.0`
* Updated `net.alchim31.maven:scala-maven-plugin:4.9.2` to `4.9.5`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.4.0` to `3.4.1`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.13.0` to `3.14.0`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.3` to `3.1.4`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.11.1` to `3.11.2`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.6.0` to `1.7.0`

### Extension

#### Compile Dependency Updates

* Updated `@exasol/extension-manager-interface:0.4.3` to `0.5.0`

#### Development Dependency Updates

* Updated `eslint:9.18.0` to `9.22.0`
* Updated `ts-jest:^29.2.5` to `^29.2.6`
* Added `@types/jest:^29.5.14`
* Updated `typescript-eslint:^8.20.0` to `^8.26.0`
* Updated `typescript:^5.7.3` to `^5.8.2`
* Updated `esbuild:^0.24.2` to `^0.25.1`
* Removed `@jest/globals:^29.7.0`
* Removed `@types/node:^22.10.6`
