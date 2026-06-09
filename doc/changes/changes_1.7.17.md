# Kafka Connector Extension 1.7.17, released 2026-??-??

Code name: Fixed vulnerability CVE-2026-47065 in org.apache.mina:mina-core:jar:2.2.4:test

## Summary

This release fixes the following vulnerability:

### CVE-2026-47065 (CWE-502) in dependency `org.apache.mina:mina-core:jar:2.2.4:test`
ZDRES-232: resolveProxyClass Not Overridden - acceptMatchers Filter Bypass via java.lang.reflect.Proxy

Assessment: Fully addressed.

When the serialised stream contains a TC_PROXYCLASSDESC (the marker 
for a java.lang.reflect.Proxy ), JDKâs ObjectInputStream.readProxyDesc()
 is
dispatched. JDK then calls the default 
ObjectInputStream.resolveProxyClass(interfaces) implementation, which 
performs Class.forName(intf, false, latestUserDefinedLoader()) for EACH 
interface name and constructs the proxy class Ã¢â¬â bypassing the accepted
 classes list .

ZDRES-233: Class.forName(name, initialize=true, classLoader) in 
readClassDescriptor Triggers Static Initialiser of Allow-Listed Classes

Assessment: Fully addressed.

For ANY class on the allow-list, deserialising a stream that names it triggers the classâs 
 (static initialiser) BEFORE any instance is constructed. This means an 
attacker who supplies a class name on the allow-list (e.g., the 
developer wrote accept(âcom.myapp.*") , attacker supplies 
com.myapp.SomeClass ) causes <clinit> of SomeClass Ã¢â¬â and many 
real-world classes have side-effecting static initialisers

Both issues have been fixed.
#### References
* https://guide.sonatype.com/vulnerability/CVE-2026-47065?component-type=maven&component-name=org.apache.mina%2Fmina-core&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2026-47065
* https://lists.apache.org/thread/y7xj1bl8qo47p9bktb11hg5v6k1d4dyj

## Security

* #191: Fixed vulnerability CVE-2026-47065 in dependency `org.apache.mina:mina-core:jar:2.2.4:test`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `ch.qos.logback:logback-classic:1.5.20` to `1.5.34`
* Updated `com.exasol:error-reporting-java:1.0.1` to `1.0.2`
* Updated `com.google.code.gson:gson:2.13.1` to `2.14.0`
* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.2.1`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.58.v20250814` to `11.0.26`
* Updated `org.scala-lang.modules:scala-collection-compat_2.13:2.13.0` to `2.14.0`
* Updated `org.scala-lang:scala-library:2.13.16` to `3.8.4`
* Updated `org.slf4j:slf4j-api:2.0.17` to `2.0.18`

#### Test Dependency Updates

* Updated `com.exasol:exasol-testcontainers:7.1.7` to `7.3.0`
* Updated `com.exasol:extension-manager-integration-test-java:0.5.16` to `0.5.19`
* Updated `com.exasol:hamcrest-resultset-matcher:1.7.1` to `1.7.2`
* Updated `com.exasol:maven-project-version-getter:1.2.1` to `1.2.2`
* Updated `com.exasol:test-db-builder-java:3.6.2` to `4.0.0`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.2.1`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.2.0`
* Updated `org.apache.mina:mina-core:2.2.4` to `2.2.8`
* Updated `org.mockito:mockito-core:5.18.0` to `5.23.0`
* Updated `org.testcontainers:kafka:1.21.3` to `1.21.4`

#### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:5.4.3` to `5.6.2`
