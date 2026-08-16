# Exasol Kafka Connector Extension 2.0.1, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2026-64607, CVE-2026-19032, CVE-2026-68497

## Summary

This release fixes the following 3 vulnerabilities:

### CVE-2026-64607 (CWE-772) in dependency `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:compile`
HttpClient based on the classic i/o model fails to correctly release the underlying connection back to the connection manager if it encounters an invalid or unsupported `Content-Encoding` header value in the response message.Â Please note this defect does not affect HttpClient based on the async i/o model.

This issue affects Apache HttpComponents Client: from 5.0-alpha1 through 5.6.2.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-64607?component-type=maven&component-name=org.apache.httpcomponents.client5%2Fhttpclient5&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-64607
* https://github.com/advisories/GHSA-hjcp-jmpx-g3qm

### CVE-2026-19032 (CWE-470) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
com.fasterxml.jackson.core/jackson-databind - Unrestricted URI schemes in Path deserialization
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-19032?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-19032
* https://github.com/FasterXML/jackson-databind/pull/6129

### CVE-2026-68497 (CWE-770) in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
jackson-databind - Allocation of Resources Without Limits or Throttling
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-68497?component-type=maven&component-name=com.fasterxml.jackson.core%2Fjackson-databind&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-68497
* https://github.com/FasterXML/jackson-databind/pull/6127

## Security

* #221: Fixed vulnerability CVE-2026-64607 in dependency `org.apache.httpcomponents.client5:httpclient5:jar:5.6.2:compile`
* #222: Fixed vulnerability CVE-2026-19032 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`
* #223: Fixed vulnerability CVE-2026-68497 in dependency `com.fasterxml.jackson.core:jackson-databind:jar:2.22.1:compile`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.1` to `1.6.3`
* Updated `io.confluent:kafka-avro-serializer:8.3.0` to `8.3.1`
* Updated `org.apache.kafka:kafka-clients:4.3.0` to `8.3.1-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.8.4`

### Test Dependency Updates

* Updated `com.exasol:test-db-builder-java:4.0.1` to `4.0.2`
* Updated `io.confluent:kafka-streams-avro-serde:8.3.0` to `8.3.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.0` to `8.3.1`
* Updated `org.apache.kafka:kafka_2.13:4.3.0` to `8.3.1-ce`
