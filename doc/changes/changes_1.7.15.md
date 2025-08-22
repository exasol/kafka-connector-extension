# Kafka Connector Extension 1.7.15, released 2025-??-??

Code name: Fixed vulnerabilities CVE-2025-1948, CVE-2025-5115

## Summary

This release fixes the following 2 vulnerabilities:

### CVE-2025-1948 (CWE-400) in dependency `org.eclipse.jetty.http2:http2-common:jar:9.4.57.v20241219:compile`
In Eclipse Jetty versions 12.0.0 to 12.0.16 included, an HTTP/2 client can specify a very large value for the HTTP/2 settings parameter SETTINGS_MAX_HEADER_LIST_SIZE.
The Jetty HTTP/2 server does not perform validation on this setting, and tries to allocate a ByteBuffer of the specified capacity to encode HTTP responses, likely resulting in OutOfMemoryError being thrown, or even the JVM process exiting.

Sonatype's research suggests that this CVE's details differ from those defined at NVD. See https://ossindex.sonatype.org/vulnerability/CVE-2025-1948 for details
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2025-1948?component-type=maven&component-name=org.eclipse.jetty.http2%2Fhttp2-common&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-1948
* https://github.com/advisories/GHSA-889j-63jv-qhr8

### CVE-2025-5115 (CWE-400) in dependency `org.eclipse.jetty.http2:http2-common:jar:9.4.57.v20241219:compile`
In Eclipse Jetty, versions <=9.4.57, <=10.0.25, <=11.0.25, <=12.0.21, <=12.1.0.alpha2, an HTTP/2 client may trigger the server to send RST_STREAM frames, for example by sending frames that are malformed or that should not be sent in a particular stream state, therefore forcing the server to consume resources such as CPU and memory.

For example, a client can open a stream and then send WINDOW_UPDATE frames with window size increment of 0, which is illegal.
Per specification  https://www.rfc-editor.org/rfc/rfc9113.html#name-window_update , the server should send a RST_STREAM frame.
The client can now open another stream and send another bad WINDOW_UPDATE, therefore causing the server to consume more resources than necessary, as this case does not exceed the max number of concurrent streams, yet the client is able to create an enormous amount of streams in a short period of time.

The attack can be performed with other conditions (for example, a DATA frame for a closed stream) that cause the server to send a RST_STREAM frame.

Links:

  *   https://github.com/jetty/jetty.project/security/advisories/GHSA-mmxm-8w33-wc4h
#### References
* https://ossindex.sonatype.org/vulnerability/CVE-2025-5115?component-type=maven&component-name=org.eclipse.jetty.http2%2Fhttp2-common&utm_source=ossindex-client&utm_medium=integration&utm_content=1.8.1
* http://web.nvd.nist.gov/view/vuln/detail?vulnId=CVE-2025-5115
* https://github.com/advisories/GHSA-mmxm-8w33-wc4h

## Security

* #147: Fixed vulnerability CVE-2025-1948 in dependency `org.eclipse.jetty.http2:http2-common:jar:9.4.57.v20241219:compile`
* #148: Fixed vulnerability CVE-2025-5115 in dependency `org.eclipse.jetty.http2:http2-common:jar:9.4.57.v20241219:compile`

## Dependency Updates

### Exasol Kafka Connector Extension

#### Compile Dependency Updates

* Updated `io.confluent:kafka-avro-serializer:7.9.0` to `8.0.0`
* Updated `org.eclipse.jetty.http2:http2-common:9.4.57.v20241219` to `11.0.26`

#### Test Dependency Updates

* Updated `com.exasol:test-db-builder-java:3.6.2` to `3.6.3`
* Updated `io.confluent:kafka-streams-avro-serde:7.9.0` to `8.0.0`
* Updated `io.github.embeddedkafka:embedded-kafka-schema-registry_2.13:7.9.0` to `8.0.0`
* Updated `org.mockito:mockito-core:5.18.0` to `5.19.0`
