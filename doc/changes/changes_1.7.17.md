# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerability CVE-2025-33042 in org.apache.avro:avro:jar:1.12.0:compile

## Summary

This release fixes the following vulnerability:

### CVE-2025-33042 (CWE-94) in dependency `org.apache.avro:avro:jar:1.12.0:compile`
Improper Control of Generation of Code ('Code Injection') vulnerability in Apache Avro Java SDK when generating specific records from untrusted Avro schemas.

This issue affects Apache Avro Java SDK: all versions through 1.11.4 and versionÂ 1.12.0.

Users are recommended to upgrade to version 1.12.1 or 1.11.5, which fix the issue.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2025-33042?component-type=maven&component-name=org.apache.avro%2Favro&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-33042
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2025/33xxx/CVE-2025-33042.json
* https://github.com/advisories/GHSA-rp46-r563-jrc7
* https://gitlab.com/gitlab-org/advisories-community/-/blob/main/maven/org.apache.avro/avro/CVE-2025-33042.yml
* https://nvd.nist.gov/vuln/detail/CVE-2025-33042
* https://osv-vulnerabilities.storage.googleapis.com/Maven/GHSA-rp46-r563-jrc7.json

## Security

* #160: Fixed vulnerability CVE-2025-33042 in dependency `org.apache.avro:avro:jar:1.12.0:compile`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.31`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.google.code.gson:gson:2.13.1` to `2.13.2`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.1.1`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814` to `11.0.26`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `3.8.1`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.2.2`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.18`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `3.6.4`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.1.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.1.0`
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.5`
* Updated `org.mockito:mockito-core:5.18.0` to `5.21.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.4.5`
