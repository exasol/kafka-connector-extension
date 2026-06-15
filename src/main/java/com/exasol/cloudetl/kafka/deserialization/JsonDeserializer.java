package com.exasol.cloudetl.kafka.deserialization;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.ScalaCollections;
import com.exasol.errorreporting.ExaError;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeType;

import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class JsonDeserializer
        implements Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
    private final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs;
    private final StringDeserializer stringDeserializer;

    public JsonDeserializer(final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs,
            final StringDeserializer stringDeserializer) {
        this.fieldSpecs = fieldSpecs;
        this.stringDeserializer = stringDeserializer;
    }

    @Override
    public scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> deserialize(
            final String topic, final byte[] data) {
        try {
            final JsonNode tree = OBJECT_MAPPER.readTree(this.stringDeserializer.deserialize(topic, data));
            final Map<FieldSpecification, scala.collection.immutable.Seq<Object>> result = new LinkedHashMap<>();
            for (final FieldSpecification fieldSpec : ScalaCollections.javaList(this.fieldSpecs)) {
                if (fieldSpec instanceof ConcreteField) {
                    final JsonNode node = tree.get(((ConcreteField) fieldSpec).fieldName());
                    result.put(fieldSpec, ScalaCollections.seqOf(node == null ? null : jsonNodeToObject(node)));
                } else if (fieldSpec instanceof FullRecord) {
                    result.put(fieldSpec, ScalaCollections.seqOf(OBJECT_MAPPER.writeValueAsString(tree)));
                } else {
                    throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-15")
                            .message("JSON records can only be used as full record or for extracting explicit fields.")
                            .mitigation("Please check that the provided JSON record specification is correct.")
                            .toString());
                }
            }
            return ScalaCollections.immutableMap(result);
        } catch (final IOException exception) {
            throw new KafkaConnectorException("Could not deserialize JSON record.", exception);
        }
    }

    private static Object jsonNodeToObject(final JsonNode jsonNode) {
        final JsonNodeType nodeType = jsonNode.getNodeType();
        if (nodeType == JsonNodeType.STRING) {
            return jsonNode.asText();
        } else if (nodeType == JsonNodeType.NUMBER) {
            return jsonNode.numberValue();
        } else if (nodeType == JsonNodeType.BOOLEAN) {
            return jsonNode.asBoolean();
        }
        return jsonNode.toString();
    }
}
