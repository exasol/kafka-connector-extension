package com.exasol.cloudetl.kafka.deserialization;

import java.util.ArrayList;
import java.util.List;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.ScalaCollections;
import com.exasol.errorreporting.ExaError;

public final class FieldParser {
    private FieldParser() {
    }

    public static List<GlobalFieldSpecification> get(final List<String> fields) {
        final List<GlobalFieldSpecification> result = new ArrayList<>();
        for (final String field : fields) {
            result.add(parse(field));
        }
        return result;
    }

    public static List<GlobalFieldSpecification> get(final scala.collection.immutable.Seq<String> fields) {
        return get(ScalaCollections.javaList(fields));
    }

    public static List<GlobalFieldSpecification> get(final String columnString) {
        final List<String> fields = new ArrayList<>();
        for (final String field : columnString.split(",")) {
            fields.add(field.trim());
        }
        return get(fields);
    }

    private static GlobalFieldSpecification parse(final String field) {
        switch (field) {
            case "key.*":
                return FieldSpecificationSingletons.recordKeyFields();
            case "value.*":
                return FieldSpecificationSingletons.recordValueFields();
            case "timestamp":
                return FieldSpecificationSingletons.timestampField();
            case "key":
                return FieldSpecificationSingletons.recordKey();
            case "value":
                return FieldSpecificationSingletons.recordValue();
            default:
                return parseConcreteField(field);
        }
    }

    private static GlobalFieldSpecification parseConcreteField(final String field) {
        final int separator = field.indexOf('.');
        if (separator <= 0 || separator == field.length() - 1 || hasWhitespace(field)) {
            throw invalidFormat(field);
        }
        final String recordPart = field.substring(0, separator);
        final String fieldName = field.substring(separator + 1);
        if ("key".equals(recordPart)) {
            return new RecordKeyField(fieldName);
        } else if ("value".equals(recordPart)) {
            return new RecordValueField(fieldName);
        } else {
            throw invalidFormat(field);
        }
    }

    private static boolean hasWhitespace(final String value) {
        for (int index = 0; index < value.length(); index++) {
            if (Character.isWhitespace(value.charAt(index))) {
                return true;
            }
        }
        return false;
    }

    private static KafkaConnectorException invalidFormat(final String field) {
        return new KafkaConnectorException(ExaError.messageBuilder("E-KCE-14")
                .message("Field reference {{REFERENCE}} does not have the correct format.", field)
                .mitigation("It must be one of [key, value, key.*, value.*, key.fieldName, value.fieldName, timestamp] values.")
                .toString());
    }
}
