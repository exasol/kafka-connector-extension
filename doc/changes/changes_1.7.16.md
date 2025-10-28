# Kafka Connector Extension 1.7.16, released 2025-??-??

Code name: Netty CVE fix

## Summary
This release fixes several CVEs in transient dependencies.

## Security

* #151: CVE-2025-58057: io.netty:netty-codec-compression:jar:4.2.4.Final:test
* CVE-2025-11226: CWE-20: Improper Input Validation (7.3) in ch.qos.logback:logback-core:jar:1.5.18:compile

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.18` to `1.5.20`
* Removed `com.google.code.gson:gson:2.13.1`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.1.0`
* Removed `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814`

#### Test Dependency Updates

* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.1.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.1.0`

#### Plugin Dependency Updates

* Updated `com.exasol:artifact-reference-checker-maven-plugin:0.4.3` to `0.4.4`
* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.4` to `2.0.5`
* Updated `com.exasol:project-keeper-maven-plugin:5.2.3` to `5.4.3`
* Updated `com.exasol:quality-summarizer-maven-plugin:0.2.0` to `0.2.1`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.1` to `9.0.2`
* Updated `org.apache.maven.plugins:maven-artifact-plugin:3.6.0` to `3.6.1`
* Updated `org.apache.maven.plugins:maven-clean-plugin:3.4.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.0` to `3.14.1`
* Updated `org.apache.maven.plugins:maven-enforcer-plugin:3.5.0` to `3.6.2`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.3` to `3.5.4`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.3` to `3.5.4`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.7.0` to `1.7.3`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.18.0` to `2.19.1`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.13` to `0.8.14`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.1.0.4751` to `5.2.0.4988`
