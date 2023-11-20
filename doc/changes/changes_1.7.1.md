# Exasol Kafka Connector Extension 1.7.1, released 2023-??-??

Code name: Test with Exasol v8

## Summary

This release adds integration tests with Exasol DB version 8.

## Features

* #77: Add tests with Exasol v8

## Dependency Updates

### Compile Dependency Updates

* Updated `io.confluent:kafka-avro-serializer:7.5.1` to `7.5.2`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.2` to `6.6.3`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.1` to `0.5.7`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.1` to `1.6.2`
* Updated `io.confluent:kafka-streams-avro-serde:7.5.1` to `7.5.2`
* Updated `io.netty:netty-handler:4.1.100.Final` to `4.1.101.Final`
* Updated `org.mockito:mockito-core:5.6.0` to `5.7.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.14` to `2.9.16`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.1.2` to `3.2.2`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.6.0` to `3.6.2`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.1.2` to `3.2.2`
