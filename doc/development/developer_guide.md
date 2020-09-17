# Developer Guide

Please read the common [developer guide for the Scala projects][dev-guide].

## Short Description on UDF Scripts

* The UDF scripts use the [`IMPORT FROM SCRIPT`][import-export-udf] statement.
* The `KAFKA_PATH` is an entry point that internally uses two other scripts
  (`KAFKA_IMPORT` and `KAFKA_METADATA `) to generate an import query. 
* The complete import process runs as a single transaction in the Exasol
  database.

[dev-guide]: https://github.com/exasol/import-export-udf-common-scala/blob/master/doc/development/developer_guide.md
[import-export-udf]: https://docs.exasol.com/loading_data/user_defined_import_export_using_udfs.htm
