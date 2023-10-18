# Exasol Kafka Connector Extension 1.7.0, released 2023-10-18

Code name: Extension manager support

## Summary

Adds extension manager support.

**Note** This release contains the following known vulnerabilities in dependencies:

* Compile dependencies:
    * `org.scala-lang:scala-library:jar:2.13.3`: CVE-2022-36944
* Test dependencies:
    * `io.netty:netty-handler:jar:4.1.95.Final`: CVE-2023-4586 
    * `fr.turri:aXMLRPC:jar:1.13.0`: CVE-2020-36641
    * `org.eclipse.jetty:jetty-http:jar:9.4.51.v20230217:test`: CVE-2023-40167
    * `org.eclipse.jetty.http2:http2-common:jar:9.4.51.v20230217:test`: CVE-2023-44487
    * `org.eclipse.jetty:jetty-servlets:jar:9.4.51.v20230217:test`: CVE-2023-36479
    * `org.eclipse.jetty.http2:http2-hpack:jar:9.4.51.v20230217:test`: CVE-2023-36478

## Features

* 72: Added extension manager support.

## Dependency Updates

### Compile Dependency Updates

* Updated `com.google.guava:guava:32.1.1-jre` to `32.1.3-jre`
* Updated `io.confluent:kafka-avro-serializer:7.4.1` to `7.5.1`
* Added `org.apache.kafka:kafka-clients:3.5.1`
* Added `org.xerial.snappy:snappy-java:1.1.10.5`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.1` to `6.6.2`
* Added `com.exasol:extension-manager-integration-test-java:0.5.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.0` to `1.6.1`
* Updated `com.exasol:test-db-builder-java:3.4.2` to `3.5.1`
* Updated `io.confluent:kafka-streams-avro-serde:7.4.1` to `7.5.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.4.1` to `7.5.1`
* Updated `io.netty:netty-handler:4.1.95.Final` to `4.1.100.Final`
* Added `org.apache.avro:avro:1.11.3`
* Added `org.apache.zookeeper:zookeeper:3.9.1`
* Added `org.json:json:20231013`
* Updated `org.mockito:mockito-core:5.4.0` to `5.6.0`
* Updated `org.scalatestplus:scalatestplus-mockito_2.13:1.0.0-M2` to `1.0.0-SNAP5`
* Updated `org.scalatest:scalatest_2.13:3.2.16` to `3.3.0-SNAP4`
* Added `org.testcontainers:kafka:1.19.1`

### Plugin Dependency Updates

* Updated `com.diffplug.spotless:spotless-maven-plugin:2.37.0` to `2.40.0`
* Updated `com.exasol:project-keeper-maven-plugin:2.9.9` to `2.9.12`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.3.0` to `3.4.0`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.5.0` to `3.6.0`
* Added `org.codehaus.mojo:exec-maven-plugin:3.1.0`
