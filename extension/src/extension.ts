import { ExasolExtension, registerExtension } from "@exasol/extension-manager-interface";
import { JavaBaseExtension, ScriptDefinition, convertBaseExtension, jarFileVersionExtractor } from "@exasol/extension-manager-interface/dist/base";
import { EXTENSION_DESCRIPTION } from "./extension-description";

/** Script definitions for the required scripts. */
const SCRIPTS: ScriptDefinition[] = [
    {
        name: "KAFKA_METADATA",
        type: "SET",
        parameters: "...",
        emitParameters: `partition_index DECIMAL(18,0), max_offset DECIMAL(36,0)`,
        scriptClass: "com.exasol.cloudetl.kafka.KafkaTopicMetadataReader"
    },
    {
        name: "KAFKA_IMPORT",
        type: "SET",
        parameters: "...",
        emitParameters: "...",
        scriptClass: "com.exasol.cloudetl.kafka.KafkaTopicDataImporter"
    },
    {
        name: "KAFKA_CONSUMER",
        type: "SET",
        parameters: "...",
        emitParameters: "...",
        scriptClass: "com.exasol.cloudetl.kafka.KafkaConsumerQueryGenerator"
    }
]

export function createExtension(): ExasolExtension {
    const baseExtension: JavaBaseExtension = {
        name: "Kafka Connector Extension",
        description: "Exasol Kafka Extension for accessing Apache Kafka",
        category: "cloud-storage-importer",
        version: EXTENSION_DESCRIPTION.version,
        files: [{ name: EXTENSION_DESCRIPTION.fileName, size: EXTENSION_DESCRIPTION.fileSizeBytes }],
        scripts: SCRIPTS,
        scriptVersionExtractor: jarFileVersionExtractor(/exasol-kafka-connector-extension-(\d+\.\d+\.\d+).jar/)
    }
    return convertBaseExtension(baseExtension)
}

registerExtension(createExtension())
