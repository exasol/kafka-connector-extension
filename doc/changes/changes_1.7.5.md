# Kafka Connector Extension 1.7.5, released 2024-05-07

Code name: Fix CVEs in compile and test dependencies

## Summary
This release fixes the following vulnerabilities in dependencies:
* CVE-2024-27309 in `org.apache.kafka:kafka-server-common:jar:3.6.0:test`
* CVE-2024-27309 in `org.apache.kafka:kafka-clients:jar:3.6.0:compile`
* CVE-2024-23080 in `joda-time:joda-time:jar:2.10.8:test`
* CVE-2024-29025 in `io.netty:netty-codec-http:jar:4.1.107.Final:test`

## Security

* #92: Fixed CVE-2024-29025
* #93: Fixed CVE-2024-23080
* #94: Fixed CVE-2024-27309
* #95: Fixed CVE-2024-27309

## Dependency Updates

### Exasol Kafka Connector Extension

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.8` to `0.5.10`
* Added `joda-time:joda-time:2.12.7`
* Added `org.apache.kafka:kafka-metadata:3.6.2`

#### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.0` to `2.0.2`
* Updated `com.exasol:project-keeper-maven-plugin:4.1.0` to `4.3.0`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.6.0` to `3.7.1`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.12.1` to `3.13.0`
* Updated `org.jacoco:jacoco-maven-plugin:0.8.11` to `0.8.12`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:3.10.0.2594` to `3.11.0.3922`
