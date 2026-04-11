# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-35554 in org.apache.kafka:kafka-clients:jar:3.9.1:compile

## Summary

This release fixes the following vulnerability:

### CVE-2026-35554 (CWE-362) in dependency `org.apache.kafka:kafka-clients:jar:3.9.1:compile`
A race condition in the Apache Kafka Java producer clientâs buffer pool management can cause messages to be silently delivered to incorrect topics.

When a produce batch expires due to delivery.timeout.ms while a network request containing that batch is still in flight, the batchâs ByteBuffer is prematurely deallocated and returned to the buffer pool. If a subsequent producer batchâpotentially destined for a different topicâreuses this freed buffer before the original network request completes, the buffer contents may become corrupted. This can result in messages being delivered to unintended topics without any error being reported to the producer.

Data Confidentiality:
Messages intended for one topic may be delivered to a different topic, potentially exposing sensitive data to consumers who have access to the destination topic but not the intended source topic.

Data Integrity:
Consumers on the receiving topic may encounter unexpected or incompatible messages, leading to deserialization failures, processing errors, and corrupted downstream data.

This issue affects Apache Kafka versions â¤ 3.9.1, â¤ 4.0.1, andÂ  â¤ 4.1.1.

Kafka users are advised to upgrade to 3.9.2, 4.0.2, 4.1.2, 4.2.0, or later to address this vulnerability.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2026-35554?component-type=maven&component-name=org.apache.kafka%2Fkafka-clients&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-35554
* https://issues.apache.org/jira/browse/KAFKA-19012
* https://lists.apache.org/thread/f07x7j8ovyqhjd1to25jsnqbm6wj01d6
* http://www.openwall.com/lists/oss-security/2026/04/07/6
* https://www.sonatype.com/products/sonatype-guide/oss-index-users

## Security

* #167: Fixed vulnerability CVE-2026-35554 in dependency `org.apache.kafka:kafka-clients:jar:3.9.1:compile`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.32`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.google.code.gson:gson:2.13.1` to `2.13.2`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.2.0`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814` to `11.0.26`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `3.8.3`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.2.3`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.19`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `3.6.4`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.2.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.2.0`
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.5`
* Updated `org.mockito:mockito-core:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.4.6`
