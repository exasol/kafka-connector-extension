# Kafka Connector Extension 1.7.11, released 2025-02-13

Code name: Fixed CVE-2025-24970 and CVE-2025-25193

## Summary

This update fixes CVE-2025-24970 and CVE-2025-25193 in transitive `netty` dependency used in test code.

## Security

* #127: CVE-2025-25193
* #128: CVE-2025-24970

## Dependency Updates

### Exasol Kafka Connector Extension

#### Test Dependency Updates

* Removed `io.netty:netty-codec:4.1.115.Final`
