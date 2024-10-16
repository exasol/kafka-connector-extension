# Kafka Connector Extension 1.7.8, released 2024-??-??

Code name: Fixed vulnerabilities CVE-2023-36479, CVE-2024-9823, CVE-2024-6763

## Summary

This release fixes the following 3 vulnerabilities:

### CVE-2023-36479 (CWE-149) in dependency `org.eclipse.jetty:jetty-servlets:jar:9.4.53.v20231009:test`
Eclipse Jetty Canonical Repository is the canonical repository for the Jetty project. Users of the CgiServlet with a very specific command structure may have the wrong command executed. If a user sends a request to a org.eclipse.jetty.servlets.CGI Servlet for a binary with a space in its name, the servlet will escape the command by wrapping it in quotation marks. This wrapped command, plus an optional command prefix, will then be executed through a call to Runtime.exec. If the original binary name provided by the user contains a quotation mark followed by a space, the resulting command line will contain multiple tokens instead of one. This issue was patched in version 9.4.52, 10.0.16, 11.0.16 and 12.0.0-beta2.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://ossindex.sonatype.org/vulnerability/CVE-2023-36479 for details
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2023-36479?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-servlets&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2023-36479
* https://github.com/eclipse/jetty.project/security/advisories/GHSA-3gh6-v5v9-6v9j

### CVE-2024-9823 (CWE-400) in dependency `org.eclipse.jetty:jetty-servlets:jar:9.4.53.v20231009:test`
There exists a security vulnerability in Jetty's DosFilter which can be exploited by unauthorized users to cause remote denial-of-service (DoS) attack on the server using DosFilter. By repeatedly sending crafted requests, attackers can trigger OutofMemory errors and exhaust the server's memory finally.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://ossindex.sonatype.org/vulnerability/CVE-2024-9823 for details
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2024-9823?component-type=maven&component-name=org.eclipse.jetty%2Fjetty-servlets&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2024-9823
* https://github.com/jetty/jetty.project/security/advisories/GHSA-7hcf-ppf8-5w5h

### CVE-2024-6763 (CWE-1286) in dependency `org.eclipse.jetty:jetty-http:jar:9.4.54.v20240208:test`
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

## Security

* #108: Fixed vulnerability CVE-2023-36479 in dependency `org.eclipse.jetty:jetty-servlets:jar:9.4.53.v20231009:test`
* #109: Fixed vulnerability CVE-2024-9823 in dependency `org.eclipse.jetty:jetty-servlets:jar:9.4.53.v20231009:test`
* #110: Fixed vulnerability CVE-2024-6763 in dependency `org.eclipse.jetty:jetty-http:jar:9.4.54.v20240208:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.6` to `1.5.11`
* Updated `com.fasterxml.jackson.core:jackson-core:2.17.0` to `2.18.0`
* Updated `com.google.guava:guava:33.1.0-jre` to `33.3.1-jre`
* Updated `io.confluent:kafka-avro-serializer:7.6.0` to `7.7.1`
* Updated `org.apache.avro:avro:1.11.3` to `1.12.0`
* Updated `org.apache.commons:commons-compress:1.26.1` to `1.27.1`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.11.0` to `2.12.0`
* Updated `org.xerial.snappy:snappy-java:1.1.10.5` to `1.1.10.7`

#### Test Dependency Updates

* Updated `com.exasol:extension-manager-integration-test-java:0.5.10` to `0.5.12`
* Updated `com.exasol:hamcrest-resultset-matcher:1.6.5` to `1.7.0`
* Updated `com.exasol:test-db-builder-java:3.5.4` to `3.6.0`
* Updated `com.google.protobuf:protobuf-java:3.25.5` to `4.28.2`
* Updated `io.confluent:kafka-streams-avro-serde:7.6.0` to `7.7.1`
* Updated `io.github.classgraph:classgraph:4.8.174` to `4.8.177`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.6.0` to `7.7.1`
* Updated `joda-time:joda-time:2.12.7` to `2.13.0`
* Updated `org.apache.kafka:kafka-metadata:3.6.2` to `7.7.1-ce`
* Updated `org.mockito:mockito-core:5.11.0` to `5.14.2`
* Updated `org.testcontainers:kafka:1.19.7` to `1.20.2`
