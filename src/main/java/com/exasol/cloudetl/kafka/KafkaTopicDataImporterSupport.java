package com.exasol.cloudetl.kafka;

import com.exasol.ExaIterator;
import com.exasol.ExaMetadata;

public final class KafkaTopicDataImporterSupport {
    private KafkaTopicDataImporterSupport() {
    }

    public static void run(final ExaMetadata metadata, final ExaIterator iterator) throws Exception {
        KafkaTopicDataImporter.run(metadata, iterator);
    }
}
