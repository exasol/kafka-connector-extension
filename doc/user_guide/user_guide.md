# User Guide

Exasol Kafka Connector Extension allows you to connect to Apache Kafka and
import Avro or Json formatted data from Kafka topics.  

Using the connector you can import data from a Kafka topic into an Exasol table.

## Table of Contents

- [Getting Started](#getting-started)
- [Deployment](#deployment)
- [Prepare Exasol Table](#prepare-exasol-table)
- [Avro Data Mapping](#avro-data-mapping)
- [Import From Kafka Cluster](#import-from-kafka-cluster)
- [Secure Connection to Kafka Cluster](#secure-connection-to-kafka-cluster)
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

Kafka connector requires a Schema Registry when importing avro messages that are 
serialized with a confluent schema registry on the producer side. 

Schema Registry is used to store, serve and manage Avro schemas for each Kafka
topic. Thus, it allows you to obtain the latest schema for a given Kafka topic.
As a result, you should set it up together with a Kafka cluster when you use
 ``RECORD_FORMAT=AVRO``.

## Deployment

This section describes how to deploy and prepare the user-defined functions
(UDFs) for Kafka integration.

### Download the Latest JAR File

Please download and save the latest assembled (with all dependencies included)
jar file from the [Github Releases][gh-releases].

Please ensure that the SHA256 sum of the downloaded jar is the same as the checksum
provided together with the jar file.

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
./sbtx assembly
```

The packaged jar file should be located at
`target/scala-2.12/exasol-kafka-connector-extension-<VERSION>.jar`.

### Create an Exasol BucketFS Bucket

To store the connector jar, we need to create a bucket in the Exasol Bucket File
System (BucketFS).

> Please see the section "The synchronous cluster file system BucketFS"
> in the EXASolution User Manual for more details about BucketFS.

This allows us to references the jar file in the UDF scripts.

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

## Prepare Exasol Table

### Avro preparation

You should create a corresponding table in Exasol that stores the data from
a Kafka topic.

The table column names and types should match the Kafka topic Avro schema names
and types.

Additionally, add two extra columns to the end of the table. These columns
store the Kafka metadata and help to keep track of the already imported records.

For example, create an Exasol table:

```sql
CREATE OR REPLACE TABLE <schema_name>.<table_name> (
    -- These columns match the Kafka topic schema
    SALES_ID    INTEGER,
    POSITION_ID SMALLINT,
    ARTICLE_ID  SMALLINT,
    AMOUNT      SMALLINT,
    PRICE       DECIMAL(9,2),
    VOUCHER_ID  SMALLINT,
    CANCELED    BOOLEAN
    -- Required for Kafka import UDF
    KAFKA_PARTITION DECIMAL(18, 0),
    KAFKA_OFFSET DECIMAL(36, 0),
);
```
### JSON preparation

In case you want to add whole json document in one single column, (see **AS_JSON_DOC** on: [Optional
consumer properties](#optional-properties)) then create table like this:

```sql
CREATE OR REPLACE TABLE <schema_name>.<table_name> (
    -- Single column as JSON string for Kafka topic record
    JSON_DOC_COL    VARCHAR(2000000),
    -- Required for Kafka import UDF
    KAFKA_PARTITION DECIMAL(18, 0),
    KAFKA_OFFSET DECIMAL(36, 0),
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

For example, given the following Avro record schema,

```json
{
  "type": "record",
  "name": "KafkaExasolAvroRecord",
  "fields": [
    { "name": "product", "type": "string" },
    { "name": "price", "type": { "type": "bytes", "precision": 4, "scale": 2, "logicalType": "decimal" }}
    { "name": "sale_time", "type": { "type": "long", "logicalType": "timestamp-millis" }}
  ]
}
```

you can define the following Exasol table with column types mapped respectively.

```sql
CREATE OR REPLACE TABLE <schema_name>.<table_name> (
    PRODUCT     VARCHAR(500),
    PRICE       DECIMAL(4, 2),
    SALE_TIME   TIMESTAMP,

    KAFKA_PARTITION DECIMAL(18, 0),
    KAFKA_OFFSET DECIMAL(36, 0)
);
```

Please notice that we convert Avro complex types to the JSON Strings. Use Exasol
`VARCHAR(n)` column type to store them. Depending on the size of complex type,
set the number of characters in the VARCHAR type accordingly.

## Importing Raw JSON

When specifying ``RECORD_FORMAT=JSON`` the connector expects a valid UTF-8
serialized JSON record per message. 
When using ``ÀS_JSON_DOC=true``, the record is inserted as a whole and 
the table has to be [prepared for it](#json-preparation)

If you choose to import certain fields from the json record, specify the 
``RECORD_FIELDS`` parameter with a comma separated list of fields to be imported

``RECORD_FIELDS=age,lastName,address``

and a json record like this

```json
  "firstName": "John",
  "lastName": "Smith",
  "isAlive": true,
  "age": 27,
  "address": {
    "streetAddress": "21 2nd Street",
    "city": "New York",
    "state": "NY",
    "postalCode": "10021-3100"
  }
```
would allow you to import into a table with the following structure

```sql
CREATE OR REPLACE TABLE <schema_name>.<table_name> (
    AGE         INTEGER,
    LAST_NAME   VARCHAR(255),
    ADDRESS     VARCHAR(10000),
    -- Required for Kafka import UDF
    KAFKA_PARTITION DECIMAL(18, 0),
    KAFKA_OFFSET DECIMAL(36, 0),
);
```

Note that the ``RECORD_FIELDS`` parameter is required when inserting JSON into
columns as the order of fields in json records is not deterministic.

# Import From Kafka Cluster

Several property values are required to access the Kafka
cluster when importing data from Kafka topics using the connector.

You should provide these key-value parameters:

- ``BOOTSTRAP_SERVERS``
- ``SCHEMA_REGISTRY_URL`` (only when using avro)
- ``TOPIC_NAME``
- ``TABLE_NAME``

The **BOOTSTRAP_SERVERS** is a comma-separated list of host port pairs used to
establish an initial connection to the Kafka cluster. The UDF connector will
contact all servers in the Kafka cluster, irrespective of servers specified with
this parameter. This list only defines initial hosts used to discover the full
list of Kafka servers.

The **SCHEMA_REGISTRY_URL** is an URL to the Schema Registry server. It is used
to retrieve Avro schemas of Kafka topics.

The **TOPIC_NAME** is the name of the Kafka topic we want to import Avro data
from. Please note that we only support a single topic data imports.

The **TABLE_NAME** is the Exasol table name that we have prepared and we are
going to import Kafka topic data.

For more information on Kafka import parameters, please refer to the [Kafka
consumer properties](#kafka-consumer-properties).

### Import Kafka Topic Data

```sql
IMPORT INTO <schema_name>.<table_name>
FROM SCRIPT KAFKA_CONSUMER WITH
  BOOTSTRAP_SERVERS   = '<kafka_bootstap_servers>'
  SCHEMA_REGISTRY_URL = '<schema_registry_url>'
  TOPIC_NAME          = '<kafka_topic>
  TABLE_NAME          = '<schema_name>.<table_name>'
  GROUP_ID            = 'exasol-kafka-udf-consumers';
```

For example, given the Kafka topic named `SALES-POSITIONS`, we can import its
data into `RETAIL.SALES_POSITIONS` table in Exasol:

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

Since the recent releases, Apache Kafka supports authentication of connections
to Kafka brokers from clients (producers and consumers) using either SSL or
SASL. Currently, Exasol Kafka connector supports **SSL**.

In order to use the secure connections to the Kafka cluster from the UDF, you
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

### Import Kafka Topic Data With SSL Enabled

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
  SSL_ENABLED             = 'true'
  SECURITY_PROTOCOL       = 'SSL'
  CONNECTION_NAME         = 'KAFKA_SSL_CONNECTION';
```

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

* ``RECORD_FORMAT`` - One of [avro, json]. The default value is **avro**.

* ``RECORD_FIELDS`` - A comma separated list of fields to import from the
  source record. This can help when the structure of the avro is not under your
  control and the order and/or the number of fields in the record can change at
  any time. For ``RECORD_FORMAT=json`` this is required.

* ``GROUP_ID`` - It defines the id for this type of consumer. The default value
  is **EXASOL_KAFKA_UDFS_CONSUMERS**. It is a unique string that identifies the
  consumer group this consumer belongs to.

* ``POLL_TIMEOUT_MS`` - It defines the timeout value that is the number of
  milliseconds to wait for the consumer poll function to return any data.
  The default value is **30000** milliseconds.

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

* ``AS_JSON_DOC`` - It defines the way the data will be imported into the database.
  If set to **'true'** data will be imported as one JSON document in one column. 
  Default value is **'false'**

The following properties should be provided to enable a secure connection to the
Kafka clusters.

* ``SSL_ENABLED`` - It is a boolean property that should be set to `true` in
  order to use the secure connections to the Kafka cluster. Default value is
  **'false'**.

* ``SECURITY_PROTOCOL`` - It is the protocol used to communicate with Kafka
  servers. Default value is **PLAINTEXT**.

* ``SSL_KEY_PASSWORD`` - It represents the password of the private key inside
  the keystore file.

* ``SSL_KEYSTORE_PASSWORD`` - It the store password for the keystore file.

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

[gh-releases]: https://github.com/exasol/kafka-connector-extension/releases
[schema-registry]: https://docs.confluent.io/current/schema-registry/index.html
[kafka-security]: https://kafka.apache.org/documentation/#security
[kafka-secure-clients]: https://kafka.apache.org/documentation/#security_configclients
[kafka-consumer-configs]: https://kafka.apache.org/documentation/#consumerconfigs
[avro-spec]: https://avro.apache.org/docs/current/spec.html
[exasol-types]: https://docs.exasol.com/sql_references/data_types/datatypesoverview.htm
