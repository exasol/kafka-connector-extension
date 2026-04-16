# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerabilities CVE-2024-6763, CVE-2026-2332

## Summary

This release fixes the following 2 vulnerabilities:

### CVE-2024-6763 (CWE-1286) in dependency `org.eclipse.jetty:jetty-http:jar:9.4.58.v20250814:compile`
Eclipse Jetty is a lightweight, highly scalable, Java-based web server and Servlet engine . It includes a utility class, HttpURI, for URI/URL parsing.

The HttpURI class does insufficient validation on the authority segment of a URI.  However the behaviour of HttpURI
 differs from the common browsers in how it handles a URI that would be 
considered invalid if fully validated against the RRC.  Specifically HttpURI
 and the browser may differ on the value of the host extracted from an 
invalid URI and thus a combination of Jetty and a vulnerable browser may
 be vulnerable to a open redirect attack or to a SSRF attack if the URI 
is used after passing validation checks.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-6763?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-http&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-6763
* https://github.com/jetty/jetty.project/security/advisories/GHSA-qh8g-58pp-2wxh
* https://www.sonatype.com/products/sonatype-guide/oss-index-users

### CVE-2026-2332 (CWE-444) in dependency `org.eclipse.jetty:jetty-http:jar:9.4.58.v20250814:compile`
In Eclipse Jetty, the HTTP/1.1 parser is vulnerable to request smuggling when chunk extensions are used, similar to the "funky chunks" techniques outlined here:
  *  https://w4ke.info/2025/06/18/funky-chunks.html

  *  https://w4ke.info/2025/10/29/funky-chunks-2.html

Jetty terminates chunk extension parsing atÂ \r\nÂ inside quoted strings instead of treating this as an error.

POST / HTTP/1.1
Host: localhost
Transfer-Encoding: chunked

1;ext="val
X
0

GET /smuggled HTTP/1.1
...

Note how the chunk extension does not close the double quotes, and it is able to inject a smuggled request.
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2026-2332?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-http&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-2332
* https://github.com/CVEProject/cvelistV5/blob/main/cves/2026/2xxx/CVE-2026-2332.json
* https://github.com/advisories/GHSA-355h-qmc2-wpwf
* https://gitlab.com/gitlab-org/advisories-community/-/blob/main/maven/org.eclipse.jetty/jetty-http/CVE-2026-2332.yml
* https://nvd.nist.gov/vuln/detail/CVE-2026-2332
* https://www.sonatype.com/products/sonatype-guide/oss-index-users

## Security

* #169: Fixed vulnerability CVE-2024-6763 in dependency `org.eclipse.jetty:jetty-http:jar:9.4.58.v20250814:compile`
* #170: Fixed vulnerability CVE-2026-2332 in dependency `org.eclipse.jetty:jetty-http:jar:9.4.58.v20250814:compile`

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
