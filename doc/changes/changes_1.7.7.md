# Kafka Connector Extension 1.7.7, released 2024-??-??

Code name: Fix logging, fixed vulnerability CVE-2024-7254 in com.google.protobuf:protobuf-java:jar:3.19.6:test

## Summary

This release fixes logging of the UDF by adding required libraries. The log level is `WARN` by default and can be changed by rebuilding the adapter JAR. See the [Exasol documentation](https://docs.exasol.com/db/latest/database_concepts/udf_scripts/debug_udf_script_output.htm) for how to configure logging of UDFs.

This release fixes the following vulnerability:

### CVE-2024-7254 (CWE-20) in dependency `com.google.protobuf:protobuf-java:jar:3.19.6:test`
Any project that parses untrusted Protocol Buffers dataÂ containing an arbitrary number of nested groups / series of SGROUPÂ tags can corrupted by exceeding the stack limit i.e. StackOverflow. Parsing nested groups as unknown fields with DiscardUnknownFieldsParser or Java Protobuf Lite parser, or against Protobuf map fields, creates unbounded recursions that can be abused by an attacker.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-7254?component-type=maven&component-name=com.google.protobuf%2Fprotobuf-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-7254
* https://github.com/advisories/GHSA-735f-pc8j-v9w8

## Security

* #101: Fixed vulnerability CVE-2024-7254 in dependency `com.google.protobuf:protobuf-java:jar:3.19.6:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Added `ch.qos.logback:logback-classic:1.5.8`
* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.17.2`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.3.0-jre`
* Updated `io.confluent:kafka-avro-serializer:7.6.0` to `7.7.1`
* Updated `org.apache.avro:avro:1.11.3` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.27.1`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.11.0` to `2.12.0`
* Added `org.slf4j:slf4j-api:2.0.16`
* Updated `org.xerial.snappy:snappy-java:1.1.10.5` to `1.1.10.7`

#### Test Dependency Updates

* Removed `ch.qos.logback:logback-classic:1.5.3`
* Removed `ch.qos.logback:logback-core:1.5.3`
* Updated `com.exasol:exasol-testcontainers:7.0.1` to `7.1.1`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.12`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Updated `io.confluent:kafka-streams-avro-serde:7.6.0` to `7.7.1`
* Updated `io.github.classgraph:classgraph:4.8.174` to `4.8.176`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.6.0` to `7.7.1`
* Updated `joda-time:joda-time:2.12.7` to `2.13.0`
* Updated `org.apache.kafka:kafka-metadata:3.6.2` to `7.7.1-ce`
* Updated `org.mockito:mockito-core:5.11.0` to `5.13.0`
* Updated `org.testcontainers:kafka:1.19.7` to `1.20.1`

#### Plugin Dependency Updates

* Updated `org.itsallcode:openfasttrace-maven-plugin:1.8.0` to `2.0.0`
