# Exasol Kafka Connector Extension 2.0.1, released 2026-10-26

Code name: Dependency security updates

## Summary

This update fixes 56 dependency vulnerabilities.

## Security

* #253: Fixed CVE-2026-15432 (timing side-channel information exposure) in `com.google.crypto.tink:tink:jar:1.20.0:test`
* #274: Fixed CVE-2026-102554 (unsafe deserialization) in `com.google.guava:guava:jar:32.0.1-jre:compile`
* #272: Fixed CVE-2026-18661 (authorization bypass) in `org.eclipse.jetty.ee10:jetty-ee10-annotations:jar:12.0.35:test`
* #249: Fixed CVE-2026-68498 (resource exhaustion) in `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
* #254: Fixed CVE-2026-89407 (regular expression denial of service) in `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
* #255: Fixed CVE-2026-89425 (resource exhaustion) in `com.fasterxml.jackson.core:jackson-core:jar:2.22.1:compile`
* #275: Fixed CVE-2026-18659 (authorization bypass) in `org.eclipse.jetty.ee10:jetty-ee10-servlet:jar:12.0.35:test`
* #276: Fixed CVE-2026-18662 (authorization bypass) in `org.eclipse.jetty.ee10:jetty-ee10-servlet:jar:12.0.35:test`
* #232: Fixed CVE-2026-86231 (certificate revocation check bypass) in `com.github.mwiede:jsch:jar:2.28.5:test`
* #235: Fixed CVE-2026-87795 (out-of-bounds read) in `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
* #236: Fixed CVE-2026-87823 (integer overflow) in `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
* #237: Fixed CVE-2026-89045 (infinite loop) in `com.github.luben:zstd-jni:jar:1.5.6-10:runtime`
* #266: Fixed CVE-2026-110262 (unbounded resource allocation) in `org.ow2.asm:asm-commons:jar:9.10.1:test`
* #267: Fixed CVE-2026-110270 (algorithmic complexity denial of service) in `org.ow2.asm:asm-commons:jar:9.10.1:test`
* #238: Fixed CVE-2026-19203 (HTTP request smuggling) in `org.eclipse.jetty:jetty-http:jar:12.0.37:test`
* #259: Fixed CVE-2026-104721 (path traversal) in `ch.qos.logback:logback-classic:jar:1.6.1:compile`
* #221: Fixed CVE-2026-64607 (connection resource leak) in `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:compile`
* #230: Fixed CVE-2026-71290 (TLS hostname verification bypass) in `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:compile`
* #233: Fixed CVE-2026-12611 (HTTP/2 resource exhaustion) in `org.eclipse.jetty.http2:jetty-http2-common:jar:12.0.37:test`
* #228: Fixed CVE-2026-75595 (mutual TLS authentication bypass) in `io.netty:netty-handler:jar:4.2.16.Final:test`
* #225: Fixed CVE-2026-75596 (algorithmic complexity denial of service) in `io.netty:netty-handler:jar:4.2.16.Final:test`
* #229: Fixed CVE-2026-62243 (TLS hostname verification bypass) in `io.netty:netty-handler:jar:4.2.16.Final:test`
* #277: Fixed CVE-2026-110257 (uncontrolled memory allocation) in `org.ow2.asm:asm:jar:9.10.1:test`
* #278: Fixed CVE-2026-110258 (uncontrolled memory allocation) in `org.ow2.asm:asm:jar:9.10.1:test`
* #279: Fixed CVE-2026-110259 (uncontrolled recursion) in `org.ow2.asm:asm:jar:9.10.1:test`
* #280: Fixed CVE-2026-110260 (algorithmic complexity denial of service) in `org.ow2.asm:asm:jar:9.10.1:test`
* #281: Fixed CVE-2026-110261 (algorithmic complexity denial of service) in `org.ow2.asm:asm:jar:9.10.1:test`
* #282: Fixed CVE-2026-110263 (uncontrolled recursion) in `org.ow2.asm:asm:jar:9.10.1:test`
* #268: Fixed CVE-2026-76184 (information exposure) in `org.eclipse.jetty:jetty-client:jar:12.0.37:test`
* #269: Fixed CVE-2026-77711 (information exposure) in `org.eclipse.jetty:jetty-client:jar:12.0.37:test`
* #273: Fixed CVE-2026-93879 (improper input validation) in `org.hibernate.validator:hibernate-validator:jar:8.0.2.Final:test`
* #240: Fixed CVE-2026-59739 (unauthorized path disclosure) in `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #241: Fixed CVE-2026-59969 (TLS hostname verification bypass) in `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #242: Fixed CVE-2026-79993 (missing authorization) in `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #243: Fixed CVE-2026-84439 (log injection) in `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #244: Fixed CVE-2026-84501 (log injection) in `org.apache.zookeeper:zookeeper:jar:3.9.5:test`
* #260: Fixed CVE-2026-106449 (uncontrolled recursion) in `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #261: Fixed CVE-2026-106450 (unbounded resource allocation) in `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #262: Fixed CVE-2026-106451 (time-of-check/time-of-use race) in `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #263: Fixed CVE-2026-106452 (uncontrolled memory allocation) in `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #264: Fixed CVE-2026-106453 (uncontrolled memory allocation) in `at.yawk.lz4:lz4-java:jar:1.11.1:runtime`
* #283: Fixed CVE-2026-19864 (cleartext storage of sensitive information) in `org.eclipse.jetty:jetty-security:jar:12.0.37:test`
* #270: Fixed CVE-2026-18660 (case sensitivity authorization bypass) in `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
* #234: Fixed CVE-2026-19204 (unbounded memory allocation) in `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
* #271: Fixed CVE-2026-76182 (injection) in `org.eclipse.jetty:jetty-server:jar:12.0.37:test`
* #285: Fixed CVE-2026-110879 (unsafe deserialization) in `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #222: Fixed CVE-2026-19032 (unsafe class selection) in `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #223: Fixed CVE-2026-68497 (regular expression denial of service) in `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #245: Fixed CVE-2026-91776 (resource exhaustion) in `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #246: Fixed CVE-2026-91777 (resource exhaustion) in `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #247: Fixed CVE-2026-83557 (unsafe deserialization) in `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #257: Fixed CVE-2026-108104 (double free) in `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
* #258: Fixed CVE-2026-108106 (uncontrolled memory allocation) in `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
* #248: Fixed CVE-2026-90559 (out-of-bounds write) in `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
* #251: Fixed CVE-2026-93451 (out-of-bounds write) in `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`
* #252: Fixed CVE-2026-93452 (out-of-bounds write) in `org.xerial.snappy:snappy-java:jar:1.1.10.7:runtime`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.6`
* Updated `com.exasol:import-export-udf-common-scala:2.0.3` to `2.0.5`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.4.0`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `4.4.0`
* Updated `org.slf4j:slf4j-api:2.0.18` to `2.0.20`

### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:8.0.1` to `8.0.2`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.3` to `1.7.4`
* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.3`
* Updated `com.exasol:udf-debugging-java:0.6.20` to `0.6.22`
* Updated `com.google.code.gson:gson:2.14.0` to `2.15.0`
* Added `com.squareup.wire:wire-runtime:6.4.7`
* Added `io.confluent:kafka-schema-registry:8.4.0`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.4.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `nl.jqno.equalsverifier:equalsverifier:4.5` to `4.5.2`
* Updated `org.apache.commons:commons-lang3:3.20.0` to `3.21.0`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `4.4.0`
* Updated `org.apache.mina:mina-core:2.2.9` to `2.2.10`
* Updated `org.apache.zookeeper:zookeeper:3.9.5` to `3.9.6`
* Updated `org.mockito:mockito-junit-jupiter:5.23.0` to `5.24.0`
* Removed `org.testcontainers:kafka:1.21.4`
* Added `org.testcontainers:testcontainers-kafka:2.0.5`

### Plugin Dependency Updates

* Updated `com.exasol:error-code-crawler-maven-plugin:2.1.0` to `2.1.2`
* Updated `com.exasol:project-keeper-maven-plugin:5.7.4` to `5.8.1`
* Updated `io.github.git-commit-id:git-commit-id-maven-plugin:10.0.0` to `10.0.1`
* Updated `org.apache.maven.plugins:maven-artifact-plugin:3.6.1` to `3.7.0`
* Updated `org.apache.maven.plugins:maven-compiler-plugin:3.15.0` to `3.16.0`
* Updated `org.apache.maven.plugins:maven-failsafe-plugin:3.5.6` to `3.6.0`
* Updated `org.apache.maven.plugins:maven-install-plugin:3.1.4` to `3.2.0`
* Updated `org.apache.maven.plugins:maven-jar-plugin:3.5.0` to `3.5.1`
* Updated `org.apache.maven.plugins:maven-surefire-plugin:3.5.6` to `3.6.0`
* Updated `org.apache.maven.plugins:maven-toolchains-plugin:3.2.0` to `3.3.0`
* Updated `org.codehaus.mojo:flatten-maven-plugin:1.7.3` to `1.8.0`
* Updated `org.codehaus.mojo:versions-maven-plugin:2.21.0` to `2.23.0`
* Updated `org.sonarsource.scanner.maven:sonar-maven-plugin:5.7.0.6970` to `5.8.0.7211`
