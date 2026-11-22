# Exasol Kafka Connector Extension 2.0.2, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-59171 in org.json:json:jar:20250107:test

## Summary

This release fixes the following vulnerability:

### CVE-2026-59171 (CWE-400) in dependency `org.json:json:jar:20250107:test`
JSON-java is a reference implementation of a JSON package in Java. From version 20150729 through 20260522, JSONTokener.nextSimpleValue can accumulate unbounded numeric tokens before JSONObject.stringToNumber passes them to BigInteger or BigDecimal conversion without a length limit. An application that parses attacker-controlled JSON or XML through JSONObject, JSONArray, or XML.toJSONObject can spend super-linear CPU time converting a sufficiently long numeric literal, blocking request-handling threads and causing denial of service. Strict mode and keep-strings settings in JSONParserConfiguration do not prevent the affected conversion path. Version 20260719 limits numeric conversion length.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://guide.sonatype.com/vulnerability/CVE-2026-59171 for details
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-59171?component-type=maven&component-name=org.json%2Fjson&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-59171
* https://github.com/stleary/JSON-java/pull/1065
* https://github.com/stleary/JSON-java/pull/1067
* https://github.com/stleary/JSON-java/releases/tag/20260719
* https://github.com/stleary/JSON-java/security/advisories/GHSA-ghc9-756g-vrcv

## Security

* #304: Fixed vulnerability CVE-2026-59171 in dependency `org.json:json:jar:20250107:test`

## Dependency Updates

### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.6.6` to `1.6.8`
* Updated `io.confluent:kafka-avro-serializer:8.4.0` to `8.4.1`
* Updated `org.apache.kafka:kafka-clients:4.4.0` to `8.4.1-ce`
* Updated `org.scala-lang:scala-library:2.13.18` to `3.10.1`

### Test Dependency Updates

* Updated `com.exasol:test-db-builder-java:4.0.3` to `5.0.0`
* Updated `com.squareup.wire:wire-runtime:6.4.7` to `7.2.2`
* Updated `io.confluent:kafka-schema-registry:8.4.0` to `8.4.1`
* Updated `io.confluent:kafka-streams-avro-serde:8.4.0` to `8.4.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:8.3.1` to `8.4.1`
* Updated `org.apache.commons:commons-lang3:3.21.0` to `3.22.0`
* Updated `org.apache.kafka:kafka_2.13:4.4.0` to `8.4.1-ce`
* Updated `org.apache.zookeeper:zookeeper:3.9.6` to `3.9.7`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.8.1` to `5.8.2`
