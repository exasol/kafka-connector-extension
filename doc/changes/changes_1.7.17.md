# Kafka Connector Extension 1.7.17, released 2026-06-22

Code name: Migrate to Java

## Summary

This release includes internal refactoring. It migrates the source code from Scala to Java to simplify development. It also improves test coverage.

## Refactoring

* #195: Migrate production code from Scala to Java
* #196: Migrate test code from Scala to Java
* #198: Enable UDF coverage collection for integration tests
* #199: Replace Scala collections with Java collections where possible

## Security updates

* #154: CVE-2025-12383: org.glassfish.jersey.core:jersey-client:jar:2.45:test
* #155: CVE-2025-12183: org.lz4:lz4-java:jar:1.8.0:runtime
* #156: CVE-2025-66566: org.lz4:lz4-java:jar:1.8.0:runtime
* #157: CVE-2024-29371: org.bitbucket.b_c:jose4j:jar:0.9.4:test
* #158: CVE-2026-1225: ch.qos.logback:logback-core:jar:1.5.20:compile
* #160: CVE-2025-33042: org.apache.avro:avro:jar:1.12.0:compile
* #163: CVE-2026-24308: org.apache.zookeeper:zookeeper:jar:3.8.4:test
* #165: CVE-2026-24281: org.apache.zookeeper:zookeeper:jar:3.8.4:test
* #167: CVE-2026-35554: org.apache.kafka:kafka-clients:jar:3.9.1:compile
* #169: CVE-2024-6763: org.eclipse.jetty:jetty-http:jar:9.4.58.v20250814:compile
* #170: CVE-2026-2332: org.eclipse.jetty:jetty-http:jar:9.4.58.v20250814:compile
* #172: CVE-2026-33558: org.apache.kafka:kafka-clients:jar:3.9.1:compile
* #174: CVE-2026-41409: org.apache.mina:mina-core:jar:2.2.4:test
* #176: CVE-2026-41635: org.apache.mina:mina-core:jar:2.2.4:test
* #177: CVE-2026-42778: org.apache.mina:mina-core:jar:2.2.4:test
* #179: CVE-2026-42583: io.netty:netty-codec-compression:jar:4.2.7.Final:test
* #180: CVE-2026-42577: io.netty:netty-transport-native-epoll:jar:4.2.7.Final:test
* #182: CVE-2026-45799: com.squareup.wire:wire-runtime-jvm:jar:5.1.0:test
* #183: CVE-2026-42577: io.netty:netty-transport-classes-epoll:jar:4.2.7.Final:test
* #184: CVE-2026-24281: org.apache.zookeeper:zookeeper:jar:3.8.4:test
* #185: CVE-2026-41409: org.apache.mina:mina-core:jar:2.2.4:test
* #186: CVE-2026-41635: org.apache.mina:mina-core:jar:2.2.4:test
* #187: CVE-2026-42779: org.apache.mina:mina-core:jar:2.2.4:test
* #189: CVE-2025-33042: org.apache.avro:avro:jar:1.12.0:compile
* #191: CVE-2026-47065: org.apache.mina:mina-core:jar:2.2.4:test
* #193: CVE-2026-44249: io.netty:netty-handler:jar:4.2.7.Final:test

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.34`
* Updated `com.exasol:import-export-udf-common-scala:2.0.1` to `2.0.2`
* Removed `com.google.code.gson:gson:2.13.1`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `7.9.2`
* Updated `org.apache.kafka:kafka-clients:3.9.1` to `3.9.2`
* Removed `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.3.0`
* Removed `com.exasol:extension-manager-integration-test-java:0.5.16`
* Added `com.exasol:udf-debugging-java:0.6.18`
* Added `com.google.code.gson:gson:2.13.1`
* Removed `com.sksamuel.avro4s:avro4s-core_2.13:4.1.2`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `7.9.5`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `7.9.2`
* Added `nl.jqno.equalsverifier:equalsverifier:3.19.4`
* Added `org.apache.commons:commons-lang3:3.20.0`
* Added `org.apache.kafka:kafka_2.13:3.9.2`
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.8`
* Added `org.apache.zookeeper:zookeeper:3.9.5`
* Added `org.jacoco:org.jacoco.agent:0.8.14`
* Removed `org.mockito:mockito-core:5.18.0`
* Added `org.mockito:mockito-junit-jupiter:5.18.0`
* Removed `org.scalatestplus:scalatestplus-mockito_2.13:1.0.0-SNAP5`
* Removed `org.scalatest:scalatest_2.13:3.3.0-SNAP4`

### Plugin Dependency Updates

* Removed `com.diffplug.spotless:spotless-maven-plugin:2.44.4`
* Updated `com.exasol:error-code-crawler-maven-plugin:2.0.5` to `2.0.7`
* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.6.2`
* Removed `io.github.evis:scalafix-maven-plugin_2.13:0.1.10_0.11.0`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:9.0.2` to `10.0.0`
* Removed `net.alchim31.maven:scala-maven-plugin:4.9.5`
* Updated `org.apache.maven.plugins:maven-assembly-plugin:3.7.1` to `3.8.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.14.1` to `3.15.0`
* Added `org.apache.maven.plugins:maven-dependency-plugin:3.10.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.4` to `3.5.5`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.4.2` to `3.5.0`
* Removed `org.apache.maven.plugins:maven-javadoc-plugin:3.11.2`
* Updated `org.apache.maven.plugins:maven-resources-plugin:3.3.1` to `3.5.0`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.4` to `3.5.5`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.19.1` to `2.21.0`
* Removed `org.itsallcode:openfasttrace-maven-plugin:2.3.0`
* Removed `org.scalastyle:scalastyle-maven-plugin:1.0.0`
* Removed `org.scalatest:scalatest-maven-plugin:2.2.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.2.0.4988` to `5.5.0.6356`
