# Exasol Kafka Connector Extension 1.7.3, released 2024-02-20

Code name: Custom `krb5.conf` files support.

## Summary

Implemented support for custom `krb5.conf` files.
Updated transient dependency to fix CVE-2024-25710 and CVE-2024-26308.

## Features

* #86: Add support for custom krb5.conf

## Dependency Updates

### Compile Dependency Updates

* Added `org.apache.commons:commons-compress:1.26.0`

### Plugin Dependency Updates

* Updated `com.exasol:project-keeper-maven-plugin:3.0.0` to `3.0.1`
