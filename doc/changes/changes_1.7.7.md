# Kafka Connector Extension 1.7.7, released 2024-09-25

Code name: Fix logging, fixed vulnerability CVE-2024-7254 in com.google.protobuf:protobuf-java:jar:3.19.6:test

## Summary

This release fixes logging of the UDF by adding required libraries. The log level is `WARN` by default and can be changed by rebuilding the adapter JAR. See the [Exasol documentation](https://docs.exasol.com/db/latest/database_concepts/udf_scripts/debug_udf_script_output.htm) for how to configure logging of UDFs.

This release fixes the following vulnerability:

### CVE-2024-7254 (CWE-20) in dependency `com.google.protobuf:protobuf-java:jar:3.19.6:test`
Any project that parses untrusted Protocol Buffers data containing an arbitrary number of nested groups / series of SGROUP tags can corrupted by exceeding the stack limit i.e. StackOverflow. Parsing nested groups as unknown fields with DiscardUnknownFieldsParser or Java Protobuf Lite parser, or against Protobuf map fields, creates unbounded recursions that can be abused by an attacker.

#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-7254?component-type=maven&component-name=com.google.protobuf%2Fprotobuf-java&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-7254
* https://github.com/advisories/GHSA-735f-pc8j-v9w8

## Security

* #101: Fixed vulnerability CVE-2024-7254 in dependency `com.google.protobuf:protobuf-java:jar:3.19.6:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Added `ch.qos.logback:logback-classic:1.5.6`
* Added `org.slf4j:slf4j-api:2.0.16`

#### Test Dependency Updates

* Removed `ch.qos.logback:logback-classic:1.5.3`
* Removed `ch.qos.logback:logback-core:1.5.3`
* Updated `com.exasol:exasol-testcontainers:7.0.1` to `7.1.1`
* Added `com.google.protobuf:protobuf-java:3.25.5`

#### Plugin Dependency Updates

* Updated `org.itsallcode:openfasttrace-maven-plugin:1.8.0` to `2.0.0`
