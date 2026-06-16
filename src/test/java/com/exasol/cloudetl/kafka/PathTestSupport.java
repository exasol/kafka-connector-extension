package com.exasol.cloudetl.kafka;

import static org.mockito.Mockito.mock;

import java.util.*;

import com.exasol.*;

class PathTestSupport {
    final String schema = "myDBSchema";
    Map<String, String> properties;
    ExaMetadata metadata;
    ExaImportSpecification importSpec;
    ExaExportSpecification exportSpec;

    void beforeEach() {
        this.properties = new LinkedHashMap<>(Map.of(
                "BUCKET_PATH", "s3a://my_bucket/folder1/*",
                "DATA_FORMAT", "PARQUET",
                "S3_ENDPOINT", "s3.eu-central-1.com",
                "S3_ACCESS_KEY", "s3_access_key",
                "S3_SECRET_KEY", "s3_secret_key"));
        this.metadata = mock(ExaMetadata.class);
        this.importSpec = mock(ExaImportSpecification.class);
        this.exportSpec = mock(ExaExportSpecification.class);
    }
}
