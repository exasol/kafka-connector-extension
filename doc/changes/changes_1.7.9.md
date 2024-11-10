# Kafka Connector Extension 1.7.9, released 2024-??-??

Code name: Fixed vulnerability CVE-2023-1932 in org.hibernate.validator:hibernate-validator:jar:6.1.7.Final:test

## Summary

This release fixes the following vulnerability:

### CVE-2023-1932 (CWE-79) in dependency `org.hibernate.validator:hibernate-validator:jar:6.1.7.Final:test`
A flaw was found in hibernate-validator's 'isValid' method in the org.hibernate.validator.internal.constraintvalidators.hv.SafeHtmlValidator class, which can be bypassed by omitting the tag ending in a less-than character. Browsers may render an invalid html, allowing HTML injection or Cross-Site-Scripting (XSS) attacks.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2023-1932?component-type=maven&component-name=org.hibernate.validator%2Fhibernate-validator&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2023-1932
* https://github.com/advisories/GHSA-x83m-pf6f-pf9g

## Security

* #116: Fixed vulnerability CVE-2023-1932 in dependency `org.hibernate.validator:hibernate-validator:jar:6.1.7.Final:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.6` to `1.5.12`
* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.18.1`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.3.1-jre`
* Updated `org.apache.avro:avro:1.11.4` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.27.1`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.11.0` to `2.12.0`

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.12`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Updated `com.exasol:test-db-builder-java:3.5.4` to `3.6.0`
* Updated `com.google.protobuf:protobuf-java:3.25.5` to `4.28.3`
* Updated `io.github.classgraph:classgraph:4.8.174` to `4.8.177`
* Updated `org.eclipse.jetty:jetty-http:9.4.56.v20240826` to `12.0.15`
* Updated `org.eclipse.jetty:jetty-server:9.4.56.v20240826` to `12.0.15`
* Updated `org.eclipse.jetty:jetty-servlets:9.4.56.v20240826` to `11.0.24`
* Updated `org.mockito:mockito-core:5.11.0` to `5.14.2`
* Updated `org.testcontainers:kafka:1.19.7` to `1.20.3`
