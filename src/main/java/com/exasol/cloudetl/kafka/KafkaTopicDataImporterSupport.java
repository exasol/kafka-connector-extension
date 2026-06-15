package com.exasol.cloudetl.kafka;

import com.exasol.ExaDataTypeException;
import com.exasol.ExaIterator;
import com.exasol.ExaIterationException;
import com.exasol.ExaMetadata;

public final class KafkaTopicDataImporterSupport {
    private KafkaTopicDataImporterSupport() {
    }

    public static void run(final ExaMetadata metadata, final ExaIterator iterator)
            throws ExaIterationException, ExaDataTypeException {
        KafkaTopicDataImporter.run(metadata, iterator);
    }
}
