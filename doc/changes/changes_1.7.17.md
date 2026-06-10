# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-44249 in io.netty:netty-handler:jar:4.2.7.Final:test

## Summary

This release fixes the following vulnerability:

### CVE-2026-44249 (CWE-1025) in dependency `io.netty:netty-handler:jar:4.2.7.Final:test`
Netty - IPv6 Subnet Filter Access Control Bypass due to Incorrect Masking
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-44249?component-type=maven&component-name=io.netty%2Fnetty-handler&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-44249
* https://github.com/advisories/GHSA-3qp7-7mw8-wx86

## Security

* #193: Fixed vulnerability CVE-2026-44249 in dependency `io.netty:netty-handler:jar:4.2.7.Final:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.34`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.google.code.gson:gson:2.13.1` to `2.14.0`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.2.1`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814` to `11.0.26`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `3.8.4`
* Updated `org.slf4j:slf4j-api:2.0.17` to `2.0.18`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.3.0`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.19`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `4.0.0`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.2.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.2.0`
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.8`
* Updated `org.mockito:mockito-core:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.6.2`
