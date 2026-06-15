package com.exasol.cloudetl.kafka.deserialization;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.ScalaCollections;
import com.exasol.errorreporting.ExaError;

public final class FieldParser {
    private static final Pattern FIELD_REFERENCE = Pattern.compile("(key|value)\\.([\\S]+)+");

    private FieldParser() {
    }

    public static scala.collection.immutable.Seq<GlobalFieldSpecification> get(
            final scala.collection.immutable.Seq<String> fields) {
        final List<GlobalFieldSpecification> result = new ArrayList<>();
        for (final String field : ScalaCollections.javaList(fields)) {
            result.add(parse(field));
        }
        return ScalaCollections.seq(result);
    }

    public static scala.collection.immutable.Seq<GlobalFieldSpecification> get(final String columnString) {
        final List<String> fields = new ArrayList<>();
        for (final String field : columnString.split(",")) {
            fields.add(field.trim());
        }
        return get(ScalaCollections.seq(fields));
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
        final Matcher matcher = FIELD_REFERENCE.matcher(field);
        if (matcher.matches()) {
            if ("key".equals(matcher.group(1))) {
                return new RecordKeyField(matcher.group(2));
            } else if ("value".equals(matcher.group(1))) {
                return new RecordValueField(matcher.group(2));
            }
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-13")
                    .message("Field reference can only contain 'key' or 'value' fields.")
                    .mitigation("Please check that the provided field reference is correct.")
                    .toString());
        }
        throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-14")
                .message("Field reference {{REFERENCE}} does not have the correct format.", field)
                .mitigation("It must be one of [key, value, key.*, value.*, key.fieldName, value.fieldName, timestamp] values.")
                .toString());
    }
}
