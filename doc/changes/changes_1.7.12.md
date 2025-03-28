# Kafka Connector Extension 1.7.12, released 2025-??-??

Code name: Fixed vulnerability CVE-2024-56128 in org.apache.kafka:kafka_2.13:jar:3.7.1:test

## Summary

This release fixes the following vulnerability:

### CVE-2024-56128 (CWE-303) in dependency `org.apache.kafka:kafka_2.13:jar:3.7.1:test`
Incorrect Implementation of Authentication Algorithm in Apache Kafka's SCRAM implementation.

Issue Summary:
Apache Kafka's implementation of the Salted Challenge Response Authentication Mechanism (SCRAM) did not fully adhere to the requirements of RFC 5802 [1].
Specifically, as per RFC 5802, the server must verify that the nonce sent by the client in the second message matches the nonce sent by the server in its first message.
However, Kafka's SCRAM implementation did not perform this validation.

Impact:
This vulnerability is exploitable only when an attacker has plaintext access to the SCRAM authentication exchange. However, the usage of SCRAM over plaintext is strongly
discouraged as it is considered an insecure practice [2]. Apache Kafka recommends deploying SCRAM exclusively with TLS encryption to protect SCRAM exchanges from interception [3].
Deployments using SCRAM with TLS are not affected by this issue.

How to Detect If You Are Impacted:
If your deployment uses SCRAM authentication over plaintext communication channels (without TLS encryption), you are likely impacted.
To check if TLS is enabled, review your server.properties configuration file for listeners property. If you have SASL_PLAINTEXT in the listeners, then you are likely impacted.

Fix Details:
The issue has been addressed by introducing nonce verification in the final message of the SCRAM authentication exchange to ensure compliance with RFC 5802.

Affected Versions:
Apache Kafka versions 0.10.2.0 through 3.9.0, excluding the fixed versions below.

Fixed Versions:
3.9.0
3.8.1
3.7.2

Users are advised to upgrade to 3.7.2 or later to mitigate this issue.

Recommendations for Mitigation:
Users unable to upgrade to the fixed versions can mitigate the issue by:
- Using TLS with SCRAM Authentication:
Always deploy SCRAM over TLS to encrypt authentication exchanges and protect against interception.
- Considering Alternative Authentication Mechanisms:
Evaluate alternative authentication mechanisms, such as PLAIN, Kerberos or OAuth with TLS, which provide additional layers of security.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://ossindex.sonatype.org/vulnerability/CVE-2024-56128 for details
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-56128?component-type=maven&component-name=org.apache.kafka%2Fkafka_2.13&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-56128
* https://github.com/advisories/GHSA-p7c9-8xx8-h74f

## Security

* #135: Fixed vulnerability CVE-2024-56128 in dependency `org.apache.kafka:kafka_2.13:jar:3.7.1:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.16` to `1.5.18`
* Updated `com.fasterxml.jackson.core:jackson-core:2.18.2` to `2.18.3`
* Updated `com.google.guava:guava:33.4.0-jre` to `33.4.6-jre`
* Updated `io.confluent:kafka-avro-serializer:7.7.1` to `7.9.0`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.12.0` to `2.13.0`
* Updated `org.slf4j:slf4j-api:2.0.16` to `2.0.17`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.2` to `7.1.4`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.13` to `0.5.16`
* Updated `com.google.protobuf:protobuf-java:4.29.3` to `4.30.2`
* Updated `io.confluent:kafka-streams-avro-serde:7.7.1` to `7.9.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.7.2` to `7.9.0`
* Updated `org.hibernate.validator:hibernate-validator:6.2.5.Final` to `8.0.2.Final`
* Updated `org.mockito:mockito-core:5.14.2` to `5.16.1`
* Updated `org.testcontainers:kafka:1.20.3` to `1.20.6`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:4.5.0` to `5.0.0`
