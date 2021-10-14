# User Guide

Exasol Kafka Connector Extension allows you to connect to Apache Kafka and
import Apache Avro, JSON or String formatted data from Kafka topics.

Using the connector you can import data from a Kafka topic into an Exasol table.

## Table of Contents

- [Getting Started](#getting-started)
- [Deployment](#deployment)
- [Record Format Configuration](#record-format-configuration)
- [Preparing Exasol Table](#preparing-exasol-table)
- [Avro Data Mapping](#avro-data-mapping)
- [JSON Data Mapping](#json-data-mapping)
- [Importing Records](#importing-records)
- [Secure Connection to Kafka Cluster](#secure-connection-to-kafka-cluster)
- [Importing Data From Azure Event Hubs](#importing-data-from-azure-event-hubs)
- [Kafka Consumer Properties](#kafka-consumer-properties)

## Getting Started

We assume you already have running Exasol cluster with version `6.0` or later
and Apache Kafka cluster with version `2.0.0` or later.

If you use the Confluent Kafka distribution, it should have the version `5.0.0`
or later.

### Parallelism

The connector accesses Kafka topic data in parallel. It starts parallel running
importer processes that are defined by the Kafka topic partitions.

That is, when importing data from a Kafka topic, we will be importing from each
topic partition in parallel. Therefore, it is recommended to configure your
Kafka topics with several partitions.

### Schema Registry

Kafka connector requires a Schema Registry when importing Avro messages that are
serialized with a Confluent Schema Registry on the producer side.

Schema Registry is used to store, serve and manage Avro schemas for each Kafka
topic. Thus, it allows you to obtain the latest schema for a given Kafka topic.
As a result, you should set it up together with a Kafka cluster when you are
importing Avro records.

## Deployment

This section describes how to deploy and prepare the user-defined functions
(UDFs) for Kafka integration.

### Download the Latest JAR File

Please download and save the latest assembled (with all dependencies included)
jar file from the [Github Releases][gh-releases].

Please ensure that the SHA256 sum of the downloaded jar is the same as the
checksum provided together with the jar file.

To check the SHA256 sum of the downloaded jar, run the command:

```sh
sha256sum exasol-kafka-connector-extension-<VERSION>.jar
```

### Building From Source

Additionally, you can build the assembled jar from the source. This allows you
to use the latest commits that may not be released yet.

Clone the repository,

```bash
git clone https://github.com/exasol/kafka-connector-extension

cd kafka-connector-extension
```

Create an assembled jar file,

```bash
sbt assembly
```

The packaged jar file should be located at
`target/scala-2.12/exasol-kafka-connector-extension-<VERSION>.jar`.

### Create an Exasol BucketFS Bucket

To store the connector jar, we need to create a bucket in the Exasol Bucket File
System (BucketFS).

> Please see the section "The synchronous cluster file system BucketFS"
> in the EXASolution User Manual for more details about BucketFS.

This allows us to reference the jar file in the UDF scripts.

### Upload the JAR File to the Bucket

Now you can upload the jar file to the bucket. However, before uploading the
jar, please make sure the BucketFS ports are open.

> In this guide, we use the port number `2580` for the HTTP protocol.

Upload the jar file using the `curl` command:

```bash
curl -X PUT -T exasol-kafka-connector-extension-<VERSION>.jar \
  http://w:<WRITE_PASSWORD>@<EXASOL_DATANODE>:2580/<BUCKET_NAME>/
```

Please ensure that the file was uploaded.

Check the bucket contents:

```bash
curl -X GET http://r:<READ_PASSWORD>@<EXASOL_DATANODE>:2580/<BUCKET_NAME>/
```

### Create UDF Scripts

Create the UDF scripts that help with importing the data.

First, create a schema that will contain the UDF scripts.

```sql
CREATE SCHEMA KAFKA_EXTENSION;
```

Run the following SQL statements to create the Kafka extension UDF scripts.

```sql
OPEN SCHEMA KAFKA_EXTENSION;

CREATE OR REPLACE JAVA SET SCRIPT KAFKA_CONSUMER(...) EMITS (...) AS
  %scriptclass com.exasol.cloudetl.kafka.KafkaConsumerQueryGenerator;
  %jar /buckets/bfsdefault/<BUCKET>/exasol-kafka-connector-extension-<VERSION>.jar;
/

CREATE OR REPLACE JAVA SET SCRIPT KAFKA_IMPORT(...) EMITS (...) AS
  %scriptclass com.exasol.cloudetl.kafka.KafkaTopicDataImporter;
  %jar /buckets/bfsdefault/<BUCKET>/exasol-kafka-connector-extension-<VERSION>.jar;
/

CREATE OR REPLACE JAVA SET SCRIPT KAFKA_METADATA(
  params VARCHAR(2000),
  kafka_partition DECIMAL(18, 0),
  kafka_offset DECIMAL(36, 0)
)
EMITS (partition_index DECIMAL(18, 0), max_offset DECIMAL(36,0)) AS
  %scriptclass com.exasol.cloudetl.kafka.KafkaTopicMetadataReader;
  %jar /buckets/bfsdefault/<BUCKET>/exasol-kafka-connector-extension-<VERSION>.jar;
```

Please do not change the UDF script names.

Similarly, do not forget to change the bucket name or the latest jar version
according to your deployment setup.

## Record Format Configuration

The connector extension can handle different Kafka record formats, currently
they are:

* Avro
* JSON
* String

The format of the records can be set for both key and value with the following
configuration values:

`RECORD_KEY_FORMAT=avro|json|string`

`RECORD_VALUE_FORMAT=avro|json|string`

It should match the format of the records on the topic you are importing. The
connector can extract fields from the key and value of the records and insert
them into the target table the import is running against.

The configuration setting `RECORD_FIELDS` controls the list of values which are
inserted into the table.

Please note that when using Avro format, you are required to provide Confluent
Schema Registry URL address.

The following table illustrates the possible values and support for the
serialization formats.

| Record Field Specification | Value                                  | Avro          | JSON                          | String |
|----------------------------|----------------------------------------|---------------|-------------------------------|--------|
| `value._field1_`           | The field _field1_ of the record value | yes           | yes                           | no     |
| `key._field2_`             | The field _field2_ of the record key   | yes           | yes                           | no     |
| `value.*`                  | All fields from the record value       | yes           | no (order not deterministic)  | no     |
| `key.*`                    | All fields from the record key         | yes           | no (order not deterministic)  | no     |
| `value`                    | The record value as string             | yes (as JSON) | yes (as JSON)                 | yes    |
| `key`                      | The record key as string               | yes (as JSON) | yes (as JSON)                 | yes    |
| `timestamp`                | The record timestamp                   | yes           | yes                           | yes    |

### Example

Given a record that has the following Avro value (JSON representation)

```json
{
  "firstName": "John",
  "lastName": "Smith",
  "isAlive": true,
  "age": 27
}
```

and this key (also Avro, as JSON representation)

```json
{
  "id": 123
}
```

Then you can configure the connector with parameters as shown below:

```
RECORD_KEY_FORMAT=avro
RECORD_VALUE_FORMAT=avro
RECORD_FIELDS=key.id,timestamp,value.lastName,value.age
```

This imports field from the record key, the fields `lastName` and `age` from
value and the record timestamp metadata.

## Preparing Exasol Table

You should create a corresponding table in Exasol that stores the data from
a Kafka topic.

The table column names and types should match the fields specified in the
`RECORD_FIELDS` parameter. Please make sure the number of columns and their
order is correct &mdash; otherwise the import will lead to an error as there is
no by-name mapping possible.

Additionally, add two extra columns to the end of the table. These columns store
the Kafka metadata and help to keep track of the already imported records.

For example, given the following Avro record schema,

```json
{
  "type": "record",
  "name": "KafkaExasolAvroRecord",
  "fields": [
    { "name": "product", "type": "string" },
    { "name": "price", "type": { "type": "bytes", "precision": 4, "scale": 2, "logicalType": "decimal" }},
    { "name": "sale_time", "type": { "type": "long", "logicalType": "timestamp-millis" }}
  ]
}
```

and the setting

`RECORD_FIELDS=value.product,value.price,value.sale_time`

then, you should define the following Exasol table with column types mapped
respectively.

```sql
CREATE OR REPLACE TABLE <schema_name>.<table_name> (
    PRODUCT     VARCHAR(500),
    PRICE       DECIMAL(4, 2),
    SALE_TIME   TIMESTAMP,

    KAFKA_PARTITION DECIMAL(18, 0),
    KAFKA_OFFSET DECIMAL(36, 0)
);
```

The last two columns are used to store the metadata about Kafka topic partition
and record offset inside a partition:

- KAFKA_PARTITION DECIMAL(18,0)
- KAFKA_OFFSET    DECIMAL(36, 0)

## Avro Data Mapping

[Avro][avro-spec] supports several primitive and complex type. The following
table shows how they are mapped to the Exasol types.

| Avro Data Type | Avro Logical Attribute | Recommended Exasol Column Types |
|:---------------|:-----------------------|:--------------------------------|
| boolean        |                        | BOOLEAN                         |
| int            |                        | INT, INTEGER, DECIMAL(18, 0)    |
| int            | date                   | DATE                            |
| long           |                        | BIGINT, DECIMAL(36, 0)          |
| long           | timestamp-millis       | TIMESTAMP                       |
| long           | timestamp-micros       | TIMESTAMP                       |
| float          |                        | FLOAT                           |
| double         |                        | DOUBLE, DOUBLE PRECISION        |
| bytes          |                        | VARCHAR(n), CHAR(n)             |
| bytes          | decimal(p, s)          | DECIMAL(p, s)                   |
| fixed          |                        | VARCHAR(n), CHAR(n)             |
| fixed          | decimal(p, s)          | DECIMAL(p, s)                   |
| string         |                        | VARCHAR(n), CHAR(n)             |
| enum           |                        | VARCHAR(n), CHAR(n)             |
| union          |                        | Corresponding Non Null Type     |
| array          |                        | VARCHAR(n), CHAR(n)             |
| map            |                        | VARCHAR(n), CHAR(n)             |
| record         |                        | VARCHAR(n), CHAR(n)             |

You can also enrich regular Avro types with logical type attributes, and use the
suggested [Exasol column types][exasol-types] when preparing the table.

Please notice that we convert Avro complex types to the JSON Strings. Use Exasol
`VARCHAR(n)` column type to store them. Depending on the size of complex type,
set the number of characters in the VARCHAR type accordingly.

## JSON Data Mapping

[JSON][json-spec] supports a limited set of primitive and complex type. The
following table shows how they are mapped to the Exasol types.

| JSON Data Type | Recommended Exasol Column Types |
|:---------------|:--------------------------------|
| boolean        | BOOLEAN                         |
| number         | INT, INTEGER, DECIMAL(p,s)      |
| string         | VARCHAR(n), CHAR(n)             |
| object         | VARCHAR(n)                      |

Similar to Avro, connector will emit any complex types as valid JSON strings, so
you should define them as `VARCHAR(n)` column in Exasol table.

## Importing Records

Several property values are required to access the Kafka cluster when importing
data from Kafka topics using the connector.

You should provide these key-value parameters:

- ``BOOTSTRAP_SERVERS``
- ``SCHEMA_REGISTRY_URL``
- ``TOPIC_NAME``
- ``TABLE_NAME``

The **BOOTSTRAP_SERVERS** is a comma-separated list of host port pairs used to
establish an initial connection to the Kafka cluster. The UDF connector will
contact all servers in the Kafka cluster, irrespective of servers specified with
this parameter. This list only defines initial hosts used to discover the full
list of Kafka servers.

The **SCHEMA_REGISTRY_URL** is an URL to the Schema Registry server. **This is
only required if you are importing Avro records.** It is used to retrieve Avro
schemas of Kafka topics. Avro is set as default record value format.

The **TOPIC_NAME** is the name of the Kafka topic we want to import Avro data
from. Please note that we only support a single topic data imports.

The **TABLE_NAME** is the Exasol table name that we have prepared and we are
going to import Kafka topic data.

For more information on Kafka import parameters, please refer to the [Kafka
consumer properties](#kafka-consumer-properties).

The import command has the following form:

```sql
IMPORT INTO <schema_name>.<table_name>
FROM SCRIPT KAFKA_CONSUMER WITH
  BOOTSTRAP_SERVERS   = '<kafka_bootstap_servers>'
  SCHEMA_REGISTRY_URL = '<schema_registry_url>'
  TOPIC_NAME          = '<kafka_topic>
  TABLE_NAME          = '<schema_name>.<table_name>'
  GROUP_ID            = 'exasol-kafka-udf-consumers';
```

For example, given the Kafka topic named `SALES-POSITIONS` containing Avro
encoded values, we can import its data into `RETAIL.SALES_POSITIONS` table in
Exasol:

```sql
IMPORT INTO RETAIL.SALES_POSITIONS
FROM SCRIPT KAFKA_CONSUMER WITH
  BOOTSTRAP_SERVERS   = 'kafka01.internal:9092,kafka02.internal:9093,kafka03.internal:9094'
  SCHEMA_REGISTRY_URL = 'http://schema-registry.internal:8081'
  TOPIC_NAME          = 'SALES-POSITIONS'
  TABLE_NAME          = 'RETAIL.SALES_POSITIONS'
  GROUP_ID            = 'exasol-kafka-udf-consumers';
```

## Secure Connection to Kafka Cluster

Since the recent releases, Apache Kafka supports secure connections to Kafka
brokers from clients (producers and consumers) using encryption with SSL/TLS and
authentication with various SASL mechanisms.

In order to use the encrypted connections to the Kafka cluster from the UDF, you
need to upload the consumer Truststore and Keystore files to the Exasol BucketFS
bucket so that we can access them when running the Kafka import UDF.

Upload the consumer Java Keystore format (JKS) files:

```bash
# Upload consumer client truststore JKS file

curl -X PUT -T certs/kafka.consumer.truststore.jks \
  http://w:<WRITE_PASSWORD>@<EXASOL_DATANODE>:2580/<BUCKET>/kafka.consumer.truststore.jks

# Upload consumer client keystore JKS file

curl -X PUT -T certs/kafka.consumer.keystore.jks \
  http://w:<WRITE_PASSWORD>@<EXASOL_DATANODE>:2580/<BUCKET>/kafka.consumer.keystore.jks
```

Please check out the Apache Kafka documentation on [security][kafka-security]
and [Kafka client configurations][kafka-secure-clients] for more information.

Additionally, we have to provide extra parameters to the UDF in order to enable
a secure connection to the Kafka cluster. Please check out the [Kafka consumer
properties](#kafka-consumer-properties) for secure property descriptions.

### Import Kafka Topic Data Using Encrypted Connection

First, create an Exasol named connection object and encode JKS files credentials
and locations with key-value pairs separated by a semicolon (`;`).

```sql
CREATE OR REPLACE CONNECTION KAFKA_SSL_CONNECTION
TO ''
USER ''
IDENTIFIED BY 'SSL_KEY_PASSWORD=<PASSWORD>;SSL_KEYSTORE_PASSWORD=<SSLPASSWORD>;SSL_KEYSTORE_LOCATION=/buckets/bfsdefault/<BUCKET>/keystore.jks;SSL_TRUSTSTORE_PASSWORD=<TRUSTSTOREPASS>;SSL_TRUSTSTORE_LOCATION=/buckets/bfsdefault/<BUCKET>/truststore.jks'
```

Then use the connection object with a Kafka import statement:

```sql
IMPORT INTO <schema_name>.<table_name>
FROM SCRIPT KAFKA_CONSUMER WITH
  BOOTSTRAP_SERVERS       = '<kafka_bootstap_servers>'
  SCHEMA_REGISTRY_URL     = '<schema_registry_url>'
  TOPIC_NAME              = '<kafka_topic>'
  TABLE_NAME              = '<schema_name>.<table_name>'
  GROUP_ID                = 'exasol-kafka-udf-consumers';
  -- Secure connection properties
  SECURITY_PROTOCOL       = 'SSL'
  CONNECTION_NAME         = 'KAFKA_SSL_CONNECTION';
```

### Import Kafka Topic Data With Authentication

Create an Exasol named connection object and encode credentials with key-value
pairs separated by a semicolon (`;`).

Note: Authentication can be used in conjunction with encryption. For this you
need create connection with combined authentication & encryption settings and
set ``SECURITY_PROTOCOL`` to **SASL_SSL**.

```sql
CREATE OR REPLACE CONNECTION KAFKA_SASL_CONNECTION
TO ''
USER ''
IDENTIFIED BY 'SASL_MECHANISM=PLAIN;SASL_USERNAME=<SASL_USERNAME>;SASL_PASSWORD=<SASL_PASSWORD>'
```

Then use the connection object with a Kafka import statement:

```sql
IMPORT INTO <schema_name>.<table_name>
FROM SCRIPT KAFKA_CONSUMER WITH
  BOOTSTRAP_SERVERS       = '<kafka_bootstap_servers>'
  SCHEMA_REGISTRY_URL     = '<schema_registry_url>'
  TOPIC_NAME              = '<kafka_topic>'
  TABLE_NAME              = '<schema_name>.<table_name>'
  GROUP_ID                = 'exasol-kafka-udf-consumers';
  -- Secure connection properties
  SECURITY_PROTOCOL       = 'SASL_SSL'
  CONNECTION_NAME         = 'KAFKA_SASL_CONNECTION';
```

If you need more complex SASL configuration, you can create [SASL JAAS
configuration][kafka-sasl-jaas] file, upload it to Exasol BucketFS and specify
its path into ``SASL_JAAS_LOCATION``.

## Importing Data From Azure Event Hubs

To import data from [Azure Event Hubs][azure-event-hubs], we are going to create
a secure **SASL_SSL** connection to encode the credentials.

```sql
CREATE OR REPLACE CONNECTION EVENT_HUBS_SASL_CONNECTION
TO ''
USER ''
IDENTIFIED BY 'SASL_MECHANISM=PLAIN#SASL_USERNAME=$ConnectionString#SASL_PASSWORD=<EVENT_HUBS_NAMESPACE_CONNECTION_STRING>'
```

Please don't forget to substitute `EVENT_HUBS_NAMESPACE_CONNECTION_STRING` with
your namespace connection string above. You can follow [Get an Event Hubs
connection string][event-hubs-get-connection-string] documentation to obtain it.

_You should notice that we use `#` as separator instead of usual `;`. This
required because Azure Event Hubs namespace connection string already contains
`;` in itself. Our custom separator prevents splitting it up._

### Prepare Table for Azure Event Hubs Topic

Let's also create an Exasol table that corresponds to the Azure Event Hub data.

For example, the following table:

```sql
CREATE OR REPLACE TABLE EVENT_HUBS_TOPIC (
    BODY            VARCHAR(20000),
    KAFKA_PARTITION DECIMAL(18, 0),
    KAFKA_OFFSET    DECIMAL(36, 0)
);
```

### Import Data From Azure Event Hub

Now we are ready to import data from Azure Event Hub.

Let's run the following SQL statement to import data:

```
IMPORT INTO EVENT_HUBS_TOPIC
FROM SCRIPT KAFKA_EXTENSION.KAFKA_CONSUMER WITH
    BOOTSTRAP_SERVERS    = '<EVENT_HUBS_NAMESPACE_HOST_NAME>.servicebus.windows.net:9093'
    RECORD_VALUE_FORMAT  = 'STRING'
    SECURITY_PROTOCOL    = 'SASL_SSL'
    CONNECTION_NAME      = 'EVENT_HUBS_SASL_CONNECTION'
    CONNECTION_SEPARATOR = '#'
    TABLE_NAME           = 'EVENT_HUBS_TOPIC'
    TOPIC_NAME           = '<EVENT_HUB_NAME>';
```

Please do not forget to replace with correct placeholders.

* `<EVENT_HUBS_NAMESPACE_HOST_NAME>` is a name of your Event Hubs Namespace. You
  can find this on the overview page of your Event Hub Namespace.
* `<EVENT_HUB_NAME>` is a name for the Event Hub (a topic in Apache Kafka terms)
  that holds data.

[azure-event-hubs]: https://azure.microsoft.com/en-us/services/event-hubs/
[event-hubs-get-connection-string]: https://docs.microsoft.com/en-us/azure/event-hubs/event-hubs-get-connection-string

## Kafka Consumer Properties

The following properties are related to UDFs when importing data from Kafka
clusters. Most of these properties are exactly the same as [Kafka consumer
configurations][kafka-consumer-configs].

### Required Properties

* ``BOOTSTRAP_SERVERS`` - It is a comma-separated host-port pairs of Kafka
  brokers. These addresses will be used to establish the initial connection to
  the Kafka cluster.

* ``TOPIC_NAME`` - It defines a Kafka topic name that we want to import data
  from.  We only support a single topic data imports. Therefore, it should not
  contain comma-separated list of more than one topic name.

* ``TABLE_NAME`` - It defines the Exasol table name the data will be imported.
  This is required as user-provided parameter since unfortunately, we cannot
  obtain table name from inside UDF even though we are importing data into it.

### Optional Properties

These are optional parameters with their default values.

* ``SCHEMA_REGISTRY_URL`` - It specifies an URL to the Confluent [Schema
  Registry][schema-registry] which stores Avro schemas as metadata. Schema
  Registry will be used to parse the Kafka topic Avro data schemas.

* ``RECORD_KEY_FORMAT`` - It specifies the record key format. It should be one
  of `avro`, `json` or `string` values. The default value is **string**.

* ``RECORD_VALUE_FORMAT`` - It defines the record value format. It should be one
  of `avro`, `json` or `string` values. The default value is **avro**.

* ``RECORD_FIELDS`` - A comma separated list of fields to import from the source
  record. It is recommended to set this when the structure of the Kafka records
  is not under your control and the order and/or the number of fields in the
  record can change at any time. The options are outlined [Record Format
  Configuration](#record-format-configuration) section.

  The default is dependent on the serialization format:
  - avro: <b><code>value.*</code></b> &mdash; All fields from the record will be
    imported.
  - json: <b><code>value</code></b> &mdash; The record will be imported as
    single JSON string into a column.
  - string: <b><code>value</code></b> &mdash; The record will be imported as
    single string into a column.

* ``GROUP_ID`` - It defines the id for this type of consumer. The default value
  is **EXASOL_KAFKA_UDFS_CONSUMERS**. It is a unique string that identifies the
  consumer group this consumer belongs to.

* ``POLL_TIMEOUT_MS`` - It defines the timeout value that is the number of
  milliseconds to wait for the consumer poll function to return any data. The
  default value is **30000** milliseconds.

* ``MIN_RECORDS_PER_RUN`` - It is an upper bound on the minimum number of
  records to the consumer per UDF run. The default value is **100**. That is, if
  the pull function returns fewer records than this number, we consume returned
  records and finish the UDF process. Otherwise, we continue polling more data
  until the total number of records reaches a certain threshold, for example,
  `MAX_RECORD_PER_RUN`.

* ``MAX_RECORD_PER_RUN`` - It is a lower bound on the maximum number of records
  to the consumer per UDF run. The default value is **1000000**. When the
  returned number of records from the poll is more than `MIN_RECORDS_PER_RUN`,
  we continue polling for more records until the total number reaches this
  number.

* ``MAX_POLL_RECORDS`` - It is the maximum number of records returned in a
  single call from the consumer poll method. The default value is **500**.

* ``FETCH_MIN_BYTES`` - It is the minimum amount of data the server should
  return for a fetch request. If insufficient data is available the request will
  wait for that much data to accumulate before answering the request. The
  default value is **1**.

* ``FETCH_MAX_BYTES`` - It is the maximum amount of data the server should
  return for a fetch request. The default value is **52428800**.

* ``MAX_PARTITION_FETCH_BYTES`` - It is the maximum amount of data per
  partition the server will return. The default value is **1048576**.

* ``CONSUME_ALL_OFFSETS`` - It defines whether to consume all available offsets
  of topic partitions. If it is set to **'true'**, connector will continue
  polling Kafka records up until the last offset in each partition that existed
  when the import started. It overrides any count thresholds and fully catches
  up on the topic from the last import offset (or initial start offset).
  Default value is **'false'**.

* ``AS_JSON_DOC`` - (_deprecated_) It defines the way the data will be imported
  into the database.  If set to **'true'** data will be imported as one JSON
  document in one column. Default value is **'false'**. When dealing with JSON
  it should be replaced by specifying `RECORD_VALUE_FORMAT=json` and
  `RECORD_FIELDS=value`.

The following properties should be provided to enable a secure connection to the
Kafka clusters. For the safety they must be specified in Exasol named connection
not in import statement itself.

* ``SSL_ENABLED`` - (_deprecated_) It is a boolean property that should be set
  to `true` in order to use the secure connections to the Kafka cluster. Default
  value is **'false'**. Use `SECURITY_PROTOCOL=SSL` or
  `SECURITY_PROTOCOL=SASL_SSL` instead.

* ``SECURITY_PROTOCOL`` - It is the protocol used to communicate with Kafka
  servers. Default value is **PLAINTEXT**.

* ``SSL_KEY_PASSWORD`` - It represents the password of the private key inside
  the keystore file.

* ``SSL_KEYSTORE_PASSWORD`` - It is the password for the keystore file.

* ``SSL_KEYSTORE_LOCATION`` - It represents the location of the keystore file.
  This location value should point to the keystore file that is available via
  Exasol bucket in BucketFS.

* ``SSL_TRUSTSTORE_PASSWORD`` - It is the password for the truststore file.

* ``SSL_TRUSTSTORE_LOCATION`` - It is the location of the truststore file, and
  it should refer to the truststore file stored inside a bucket in Exasol
  BucketFS.

* ``SSL_ENDPOINT_IDENTIFICATION_ALGORITHM`` - It is the endpoint identification
  algorithm to validate server hostname using a server certificate. Default
  value is **https**.

* ``SASL_MECHANISM`` - It is SASL mechanism to use for authentication.
  Default value is **GSSAPI**.

* ``SASL_USERNAME``/``SASL_PASSWORD`` - These are SASL credentials. They can be
  simply used when `SASL_MECHANISM` is set to **PLAIN**, __DIGEST-*__ or
  __SCRAM-*__.

* ``SASL_JAAS_LOCATION`` - It is the location of the JAAS configuration file for
  more complex configuration of SASL authentication. It should refer to the file
  stored inside a bucket in Exasol BucketFS.

[gh-releases]: https://github.com/exasol/kafka-connector-extension/releases
[schema-registry]: https://docs.confluent.io/current/schema-registry/index.html
[kafka-security]: https://kafka.apache.org/documentation/#security
[kafka-secure-clients]: https://kafka.apache.org/documentation/#security_configclients
[kafka-consumer-configs]: https://kafka.apache.org/documentation/#consumerconfigs
[kafka-sasl-jaas]: https://docs.confluent.io/platform/current/kafka/authentication_sasl/index.html#client-jaas-configurations
[avro-spec]: https://avro.apache.org/docs/current/spec.html
[json-spec]: https://www.json.org/json-en.html
[exasol-types]: https://docs.exasol.com/sql_references/data_types/datatypesoverview.htm
