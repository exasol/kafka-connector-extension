# Exasol Kafka Connector Extension 1.7.2, released 2023-12-20

Code name: Fix CVE-2023-6378 in `logback` test dependencies

This release fixes CVE-2023-6378 in dependencies `ch.qos.logback/logback-core@1.2.10` and `ch.qos.logback/logback-classic@1.2.10` with scope `test`.

## Security

* #83: Fixed CVE-2023-6378 in `ch.qos.logback/logback-classic@1.2.10`
* #84: Fixed CVE-2023-6378 in `ch.qos.logback/logback-core@1.2.10`

## Dependency Updates

### Compile Dependency Updates

* Updated `com.google.guava:guava:32.1.3-jre` to `33.0.0-jre`

### Test Dependency Updates

* Added `ch.qos.logback:logback-classic:1.4.14`
* Added `ch.qos.logback:logback-core:1.4.14`
* Updated `com.exasol:exasol-testcontainers:6.6.3` to `7.0.0`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.2` to `1.6.3`
* Updated `com.exasol:test-db-builder-java:3.5.1` to `3.5.3`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.5.1` to `7.5.2`
* Removed `io.netty:netty-handler:4.1.101.Final`
* Updated `org.mockito:mockito-core:5.7.0` to `5.8.0`
* Updated `org.testcontainers:kafka:1.19.1` to `1.19.3`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:2.9.16` to `3.0.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.2.2` to `3.2.3`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.2.2` to `3.2.3`
* Added `org.apache.maven.plugins:maven-toolchains-plugin:3.1.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.16.1` to `2.16.2`
