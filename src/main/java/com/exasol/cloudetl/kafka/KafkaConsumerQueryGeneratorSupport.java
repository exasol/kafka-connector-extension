package com.exasol.cloudetl.kafka;

import com.exasol.ExaImportSpecification;
import com.exasol.ExaMetadata;

public final class KafkaConsumerQueryGeneratorSupport {
    private KafkaConsumerQueryGeneratorSupport() {
    }

    public static String generateSqlForImportSpec(final ExaMetadata metadata, final ExaImportSpecification importSpec) {
        return KafkaConsumerQueryGenerator.generateSqlForImportSpec(metadata, importSpec);
    }
}
