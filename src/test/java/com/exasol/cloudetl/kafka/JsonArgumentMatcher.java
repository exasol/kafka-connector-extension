package com.exasol.cloudetl.kafka;

import org.mockito.ArgumentMatcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The order of JSON fields is not deterministic, therefore this matcher compares parsed JSON trees.
 */
public final class JsonArgumentMatcher implements ArgumentMatcher<String> {
    private static final ObjectMapper JSON = new ObjectMapper();
    private final JsonNode expectedJsonNode;

    public JsonArgumentMatcher(final String expectedJson) {
        this.expectedJsonNode = readJson(expectedJson);
    }

    @Override
    public boolean matches(final String argument) {
        return readJson(argument).equals(this.expectedJsonNode);
    }

    public static JsonNode readJson(final String json) {
        try {
            return JSON.readTree(json);
        } catch (final JsonProcessingException exception) {
            throw new IllegalArgumentException("Invalid JSON: " + json, exception);
        }
    }
}
