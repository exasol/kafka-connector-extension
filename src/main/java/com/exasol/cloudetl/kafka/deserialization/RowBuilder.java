package com.exasol.cloudetl.kafka.deserialization;

import java.util.*;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.errorreporting.ExaError;

public final class RowBuilder {
    private RowBuilder() {
    }

    public static List<Object> buildRow(final List<GlobalFieldSpecification> fieldSpecs,
            final ConsumerRecord<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> consumerRecord,
            final int outputColumnCount) {
        final List<List<Object>> rowValues = new ArrayList<>();
        final List<Boolean> present = new ArrayList<>();
        for (final GlobalFieldSpecification spec : fieldSpecs) {
            final List<Object> value = getValue(spec, consumerRecord);
            rowValues.add(value);
            present.add(value != null);
        }
        final long absentCount = present.stream().filter(isPresent -> !isPresent.booleanValue()).count();
        if (absentCount > 0) {
            validateSingleMissingExpression(absentCount);
            return buildRowWithMissingValues(rowValues, present, outputColumnCount);
        }
        return flatten(rowValues);
    }

    private static void validateSingleMissingExpression(final long absentCount) {
        if (absentCount > 1) {
            throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-12")
                    .message("Found multiple expressions that can be null.")
                    .mitigation("Please check that there is only single specification, e.g, key.* or value.*, "
                            + "that can map to multiple null columns.")
                    .toString());
        }
    }

    private static List<Object> buildRowWithMissingValues(final List<List<Object>> rowValues, final List<Boolean> present,
            final int outputColumnCount) {
        final int valuesMissing = outputColumnCount - countPresentColumns(rowValues, present);
        final List<Object> result = new ArrayList<>();
        for (int index = 0; index < rowValues.size(); index++) {
            if (present.get(index).booleanValue()) {
                result.addAll(rowValues.get(index));
            } else {
                addNulls(result, valuesMissing);
            }
        }
        return result;
    }

    private static int countPresentColumns(final List<List<Object>> rowValues, final List<Boolean> present) {
        int presentColumnCount = 0;
        for (int index = 0; index < rowValues.size(); index++) {
            if (present.get(index).booleanValue()) {
                presentColumnCount += rowValues.get(index).size();
            }
        }
        return presentColumnCount;
    }

    private static void addNulls(final List<Object> result, final int count) {
        for (int index = 0; index < count; index++) {
            result.add(null);
        }
    }

    private static List<Object> flatten(final List<List<Object>> rowValues) {
        final List<Object> result = new ArrayList<>();
        for (final List<Object> value : rowValues) {
            result.addAll(value);
        }
        return result;
    }

    private static List<Object> getValue(final GlobalFieldSpecification spec,
            final ConsumerRecord<Map<FieldSpecification, List<Object>>, Map<FieldSpecification, List<Object>>> consumerRecord) {
        return getValue(spec, consumerRecord.key(), consumerRecord.value(), consumerRecord.timestamp());
    }

    private static List<Object> getValue(final GlobalFieldSpecification spec, final Map<FieldSpecification, List<Object>> key,
            final Map<FieldSpecification, List<Object>> value, final long timestamp) {
        if (spec instanceof KeySpecification) {
            return getFromRecord(key, (FieldSpecification) spec);
        } else if (spec instanceof ValueSpecification) {
            return getFromRecord(value, (FieldSpecification) spec);
        } else if (spec == FieldSpecificationSingletons.timestampField()) {
            return List.of(timestamp);
        }
        throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-11")
                .message("Unsupported field specification {{SPECIFICATION}} is provided.", spec)
                .ticketMitigation()
                .toString());
    }

    private static List<Object> getFromRecord(final Map<FieldSpecification, List<Object>> recordPart,
            final FieldSpecification spec) {
        if (recordPart != null && recordPart.containsKey(spec)) {
            return recordPart.get(spec);
        }
        return defaultFor(spec);
    }

    private static List<Object> defaultFor(final FieldSpecification spec) {
        if (spec instanceof AllFieldsSpecification) {
            return null;
        }
        return Collections.singletonList(null);
    }
}
