# Kafka Connector Extension 1.7.8, released 2024-??-??

Code name: Fixed vulnerabilities CVE-2024-6762, CVE-2024-8184

## Summary

This release fixes the following 2 vulnerabilities:

### CVE-2024-6762 (CWE-400) in dependency `org.eclipse.jetty:jetty-servlets:jar:9.4.53.v20231009:test`
Jetty PushSessionCacheFilter can be exploited by unauthenticated users 
to launch remote DoS attacks by exhausting the serverâs memory.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://ossindex.sonatype.org/vulnerability/CVE-2024-6762 for details
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-6762?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-servlets&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-6762
* https://github.com/jetty/jetty.project/pull/10755
* https://github.com/jetty/jetty.project/pull/10756
* https://github.com/jetty/jetty.project/pull/9715
* https://github.com/jetty/jetty.project/pull/9716
* https://github.com/jetty/jetty.project/security/advisories/GHSA-r7m4-f9h5-gr79

### CVE-2024-8184 (CWE-400) in dependency `org.eclipse.jetty:jetty-server:jar:9.4.54.v20240208:test`
There exists a security vulnerability in Jetty's ThreadLimitHandler.getRemote() which can be exploited by unauthorized users to cause remote denial-of-service (DoS) attack.  By repeatedly sending crafted requests, attackers can trigger OutofMemory errors and exhaust the server's memory.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-8184?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-server&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-8184
* https://github.com/jetty/jetty.project/security/advisories/GHSA-g8m5-722r-8whq

## Security

* #112: Fixed vulnerability CVE-2024-6762 in dependency `org.eclipse.jetty:jetty-servlets:jar:9.4.53.v20231009:test`
* #113: Fixed vulnerability CVE-2024-8184 in dependency `org.eclipse.jetty:jetty-server:jar:9.4.54.v20240208:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.6` to `1.5.11`
* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.18.0`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.3.1-jre`
* Updated `io.confluent:kafka-avro-serializer:7.6.0` to `7.7.1`
* Updated `org.apache.avro:avro:1.11.3` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.27.1`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.11.0` to `2.12.0`
* Updated `org.xerial.snappy:snappy-java:1.1.10.5` to `1.1.10.7`

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.12`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Updated `com.exasol:test-db-builder-java:3.5.4` to `3.6.0`
* Updated `com.google.protobuf:protobuf-java:3.25.5` to `4.28.2`
* Updated `io.confluent:kafka-streams-avro-serde:7.6.0` to `7.7.1`
* Updated `io.github.classgraph:classgraph:4.8.174` to `4.8.177`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.6.0` to `7.7.1`
* Updated `joda-time:joda-time:2.12.7` to `2.13.0`
* Updated `org.apache.kafka:kafka-metadata:3.6.2` to `7.7.1-ce`
* Updated `org.mockito:mockito-core:5.11.0` to `5.14.2`
* Updated `org.testcontainers:kafka:1.19.7` to `1.20.2`
