# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-41409 in org.apache.mina:mina-core:jar:2.2.4:test

## Summary

This release fixes the following vulnerability:

### CVE-2026-41409 (CWE-502) in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
The fix for CVE-2024-52046 in Apache MINA AbstractIoBuffer.getObject() was incomplete. The classname allowlist of classes allowed to be deserialized was applied too late after a static initializer in a class to be read might already have been executed.

Affected versions are Apache MINA 2.0.0 <= 2.0.27, 2.1.0 <= 2.1.10, and 2.2.0 <= 2.2.5.

The problem is resolved in Apache MINA 2.0.28, 2.1.11, and 2.2.6 by 
applying the classname allowlist earlier.

Affected are applications using Apache MINA that call IoBuffer.getObject().

Applications using Apache MINA are advised to upgrade
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-41409?component-type=maven&component-name=org.apache.mina%2Fmina-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-41409
* https://lists.apache.org/thread/9ddvsq6c4l5bhwq8l14sob4f8qjvx5c9

## Security

* #174: Fixed vulnerability CVE-2026-41409 in dependency `org.apache.mina:mina-core:jar:2.2.4:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.32`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.google.code.gson:gson:2.13.1` to `2.14.0`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.2.0`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814` to `11.0.26`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `3.8.3`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.2.3`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.19`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `4.0.0`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.2.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.2.0`
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.6`
* Updated `org.mockito:mockito-core:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.5.1`
