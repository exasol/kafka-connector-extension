# Kafka Connector Extension 1.7.10, released 2025-??-??

Code name: Security fixes in transitive dependencies

## Summary
Fixes several security issues in transitive dependencies: CVE-2024-56128, CVE-2024-12798, CVE-2024-12801 and CVE-2024-52046.
Project keeper was upgraded to the latest version.

## Security

* #121: CVE-2024-56128: org.apache.kafka:kafka_2.13:jar:3.7.1:test
* #122: CVE-2024-12798: ch.qos.logback:logback-core:jar:1.5.12:compile
* #123: CVE-2024-12801: ch.qos.logback:logback-core:jar:1.5.12:compile
* #124: CVE-2024-52046: org.apache.mina:mina-core:jar:2.2.3:test

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.12` to `1.5.16`
* Updated `org.apache.kafka:kafka-clients:3.7.1` to `3.7.2`

#### Test Dependency Updates

* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.7.1` to `7.7.2`
* Added `org.apache.mina:mina-core:2.2.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.4.0` to `4.5.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.1` to `3.5.2`
* Updated `org.apache.maven.plugins:maven-site-plugin:3.9.1` to `3.21.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.1` to `3.5.2`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.17.1` to `2.18.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:4.0.0.4121` to `5.0.0.4389`

### Extension

#### Development Dependency Updates

* Updated `eslint:9.14.0` to `9.18.0`
* Updated `@types/node:^22.9.1` to `^22.10.6`
* Updated `typescript-eslint:^8.14.0` to `^8.20.0`
* Updated `typescript:^5.6.3` to `^5.7.3`
* Updated `esbuild:^0.24.0` to `^0.24.2`
