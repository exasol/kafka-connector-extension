# Kafka Connector Extension 1.7.12, released 2025-??-??

Code name: Fixed vulnerability CVE-2024-58103 in com.squareup.wire:wire-runtime-jvm:jar:4.9.7:test

## Summary

This release fixes the following vulnerability:

### CVE-2024-58103 (CWE-674) in dependency `com.squareup.wire:wire-runtime-jvm:jar:4.9.7:test`
Square Wire before 5.2.0 does not enforce a recursion limit on nested groups in ByteArrayProtoReader32.kt and ProtoReader.kt.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-58103?component-type=maven&component-name=com.squareup.wire%2Fwire-runtime-jvm&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-58103
* https://github.com/advisories/GHSA-pwf9-q62p-v7wc

## Security

* #131: Fixed vulnerability CVE-2024-58103 in dependency `com.squareup.wire:wire-runtime-jvm:jar:4.9.7:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.16` to `1.5.17`
* Updated `com.fasterxml.jackson.core:jackson-core:2.18.2` to `2.18.3`
* Updated `io.confluent:kafka-avro-serializer:7.7.1` to `7.9.0`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.12.0` to `2.13.0`
* Updated `org.slf4j:slf4j-api:2.0.16` to `2.0.17`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.2` to `7.1.4`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.13` to `0.5.16`
* Updated `com.google.protobuf:protobuf-java:4.29.3` to `4.30.1`
* Updated `io.confluent:kafka-streams-avro-serde:7.7.1` to `7.9.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.7.2` to `7.9.0`
* Updated `org.hibernate.validator:hibernate-validator:6.2.5.Final` to `8.0.2.Final`
* Updated `org.mockito:mockito-core:5.14.2` to `5.16.1`
* Updated `org.testcontainers:kafka:1.20.3` to `1.20.6`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.5.0` to `5.0.0`
