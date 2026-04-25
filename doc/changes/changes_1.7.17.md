# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-33558 in org.apache.kafka:kafka-clients:jar:3.9.1:compile

## Summary

This release fixes the following vulnerability:

### CVE-2026-33558 (CWE-533) in dependency `org.apache.kafka:kafka-clients:jar:3.9.1:compile`
Information exposure vulnerability has been identified in Apache Kafka.

The NetworkClient component will output entire requests and responses information in the DEBUG log level in the logs. By default, the log level is set to INFO level. If the DEBUG level is enabled, the sensitive information will be exposed via the requests and responses output log. The entire lists of impacted requests and responses are:

  *  AlterConfigsRequest

  *  AlterUserScramCredentialsRequest

  *  ExpireDelegationTokenRequest

  *  IncrementalAlterConfigsRequest

  *  RenewDelegationTokenRequest

  *  SaslAuthenticateRequest

  *  createDelegationTokenResponse

  *  describeDelegationTokenResponse

  *  SaslAuthenticateResponse

This issue affects Apache Kafka: from any version supported the listed API above through v3.9.1, v4.0.0. We advise the Kafka users to upgrade to v3.9.2, v4.0.1, or later to avoid this vulnerability.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2026-33558?component-type=maven&component-name=org.apache.kafka%2Fkafka-clients&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-33558
* https://lists.apache.org/thread/pz5g4ky3h0k91tfd14p0dzqjp80960kl
* https://www.sonatype.com/products/sonatype-guide/oss-index-users

## Security

* #172: Fixed vulnerability CVE-2026-33558 in dependency `org.apache.kafka:kafka-clients:jar:3.9.1:compile`

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
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.5`
* Updated `org.mockito:mockito-core:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.5.0`
