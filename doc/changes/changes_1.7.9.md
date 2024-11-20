# Kafka Connector Extension 1.7.9, released 2024-11-20

Code name: Fixed vulnerabilities CVE-2024-47535 and CVE-2023-1932

## Summary

This release fixes the following vulnerability:

### CVE-2024-47535 (CWE-400) in dependency `io.netty:netty-common:jar:4.1.108.Final:test`
Netty is an asynchronous event-driven network application framework for rapid development of maintainable high performance protocol servers & clients. An unsafe reading of environment file could potentially cause a denial of service in Netty. When loaded on an Windows application, Netty attempts to load a file that does not exist. If an attacker creates such a large file, the Netty application crashes. This vulnerability is fixed in 4.1.115.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-47535?component-type=maven&component-name=io.netty%2Fnetty-common&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-47535
* https://github.com/advisories/GHSA-xq3w-v528-46rv

### CVE-2023-1932 (CWE-79) in dependency `org.hibernate.validator:hibernate-validator:jar:6.1.7.Final:test`
A flaw was found in hibernate-validator's 'isValid' method in the org.hibernate.validator.internal.constraintvalidators.hv.SafeHtmlValidator class, which can be bypassed by omitting the tag ending in a less-than character. Browsers may render an invalid html, allowing HTML injection or Cross-Site-Scripting (XSS) attacks.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2023-1932?component-type=maven&component-name=org.hibernate.validator%2Fhibernate-validator&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2023-1932
* https://github.com/advisories/GHSA-x83m-pf6f-pf9g

## Security

* #118: Fixed vulnerability CVE-2024-47535 in dependency `io.netty:netty-common:jar:4.1.108.Final:test`
* #116: Fixed vulnerability CVE-2023-1932 in dependency `org.hibernate.validator:hibernate-validator:jar:6.1.7.Final:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.6` to `1.5.12`
* Added `com.exasol:import-export-udf-common-scala:2.0.1`
* Removed `com.exasol:import-export-udf-common-scala_2.13:2.0.0`
* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.18.1`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.3.1-jre`
* Updated `org.apache.avro:avro:1.11.4` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.27.1`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.11.0` to `2.12.0`
* Updated `org.scala-lang:scala-library:2.13.12` to `2.13.15`

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.13`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Added `com.exasol:maven-project-version-getter:1.2.0`
* Updated `com.exasol:test-db-builder-java:3.5.4` to `3.6.0`
* Updated `com.google.protobuf:protobuf-java:3.25.5` to `4.28.3`
* Updated `io.github.classgraph:classgraph:4.8.174` to `4.8.179`
* Added `io.netty:netty-codec:4.1.115.Final`
* Updated `org.eclipse.jetty:jetty-http:9.4.56.v20240826` to `12.0.15`
* Updated `org.eclipse.jetty:jetty-server:9.4.56.v20240826` to `12.0.15`
* Updated `org.eclipse.jetty:jetty-servlets:9.4.56.v20240826` to `11.0.24`
* Added `org.hibernate.validator:hibernate-validator:8.0.0.Final`
* Updated `org.mockito:mockito-core:5.11.0` to `5.14.2`
* Updated `org.testcontainers:kafka:1.19.7` to `1.20.3`

#### Plugin Dependency Updates

* Updated `io.github.evis:scalafix-maven-plugin_2.13:0.1.8_0.11.0` to `0.1.10_0.11.0`
* Updated `net.alchim31.maven:scala-maven-plugin:4.8.1` to `4.9.2`
* Updated `org.apache.maven.plugins:maven-javadoc-plugin:3.6.3` to `3.11.1`
* Updated `org.codehaus.mojo:exec-maven-plugin:3.2.0` to `3.5.0`
* Updated `org.itsallcode:openfasttrace-maven-plugin:2.0.0` to `2.3.0`

### Extension

#### Compile Dependency Updates

* Updated `@exasol/extension-manager-interface:0.4.1` to `0.4.3`

#### Development Dependency Updates

* Updated `eslint:^8.57.0` to `9.14.0`
* Updated `@types/node:^20.11.28` to `^22.9.1`
* Updated `ts-jest:^29.1.2` to `^29.2.5`
* Added `typescript-eslint:^8.14.0`
* Updated `typescript:^5.4.2` to `^5.6.3`
* Updated `esbuild:^0.20.2` to `^0.24.0`
* Removed `@typescript-eslint/parser:^7.2.0`
* Removed `@typescript-eslint/eslint-plugin:^7.2.0`
