# Exasol Kafka Connector Extension 1.7.0, released 2023-10-17

Code name: Extension manager support

## Summary

Adds extension manager support.

## Features

* 72: Adds extension manager support.

* Excluded the following CVEs in test dependencies:
    * io.netty:netty-handler:jar:4.1.95.Final:test
        * CVE-2023-4586 
    * fr.turri:aXMLRPC:jar:1.13.0:test
        * CVE-2020-36641
    * org.eclipse.jetty:jetty-http:jar:9.4.51.v20230217:test
        * CVE-2023-40167
    * org.eclipse.jetty:jetty-servlets:jar:9.4.51.v20230217:test
        * CVE-2023-36479
    * org.xerial.snappy:snappy-java:jar:1.1.10.1:runtime; vulnerability under org.testcontainers:kafka:jar:1.19.0:test
        * CVE-2023-43642
    * org.eclipse.jetty.http2:http2-common:jar:9.4.51.v20230217:test
        * CVE-2023-44487
    * org.apache.avro:avro:jar:1.9.2:test 
        * CVE-2023-39410
    * org.eclipse.jetty.http2:http2-hpack:jar:9.4.51.v20230217:test
        * CVE-2023-36478
    * org.apache.zookeeper:zookeeper:jar:3.6.3:test
        * CVE-2023-44981
    * org.json:json:jar:20230227:test
        * CVE-2023-5072

## Dependency Updates

### Compile Dependency Updates

* Updated `com.google.guava:guava:32.1.1-jre` to `32.1.3-jre`
* Updated `io.confluent:kafka-avro-serializer:7.4.1` to `7.5.1`
* Added `org.apache.kafka:kafka-clients:3.5.1`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:6.6.1` to `6.6.2`
* Added `com.exasol:extension-manager-integration-test-java:0.5.1`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.0` to `1.6.1`
* Updated `com.exasol:test-db-builder-java:3.4.2` to `3.5.1`
* Updated `io.confluent:kafka-streams-avro-serde:7.4.1` to `7.5.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.4.1` to `7.5.0`
* Added `org.testcontainers:kafka:1.19.0`
* Added `org.testcontainers:testcontainers:1.19.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.9` to `2.9.12`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.3.0` to `3.4.0`
* Added `org.codehaus.mojo:exec-maven-plugin:3.1.0`
