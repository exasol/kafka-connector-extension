package com.exasol.cloudetl.kafka.deserialization;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import com.exasol.cloudetl.kafka.KafkaConnectorException;
import com.exasol.cloudetl.kafka.ScalaCollections;
import com.exasol.errorreporting.ExaError;

public final class RowBuilder {
    private RowBuilder() {
    }

    public static scala.collection.immutable.Seq<Object> buildRow(
            final scala.collection.immutable.Seq<GlobalFieldSpecification> fieldSpecs,
            final ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> consumerRecord,
            final int outputColumnCount) {
        final List<scala.collection.immutable.Seq<Object>> rowValues = new ArrayList<>();
        final List<Boolean> present = new ArrayList<>();
        for (final GlobalFieldSpecification spec : ScalaCollections.javaList(fieldSpecs)) {
            final scala.collection.immutable.Seq<Object> value = getValue(spec, consumerRecord);
            rowValues.add(value);
            present.add(value != null);
        }
        final long absentCount = present.stream().filter(isPresent -> !isPresent.booleanValue()).count();
        if (absentCount > 0) {
            validateSingleMissingExpression(absentCount);
            return ScalaCollections.seq(buildRowWithMissingValues(rowValues, present, outputColumnCount));
        }
        return ScalaCollections.seq(flatten(rowValues));
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

    private static List<Object> buildRowWithMissingValues(
            final List<scala.collection.immutable.Seq<Object>> rowValues, final List<Boolean> present,
            final int outputColumnCount) {
        final int valuesMissing = outputColumnCount - countPresentColumns(rowValues, present);
        final List<Object> result = new ArrayList<>();
        for (int index = 0; index < rowValues.size(); index++) {
            if (present.get(index).booleanValue()) {
                result.addAll(ScalaCollections.javaList(rowValues.get(index)));
            } else {
                addNulls(result, valuesMissing);
            }
        }
        return result;
    }

    private static int countPresentColumns(final List<scala.collection.immutable.Seq<Object>> rowValues,
            final List<Boolean> present) {
        int presentColumnCount = 0;
        for (int index = 0; index < rowValues.size(); index++) {
            if (present.get(index).booleanValue()) {
                presentColumnCount += ScalaCollections.javaList(rowValues.get(index)).size();
            }
        }
        return presentColumnCount;
    }

    private static void addNulls(final List<Object> result, final int count) {
        for (int index = 0; index < count; index++) {
            result.add(null);
        }
    }

    private static List<Object> flatten(final List<scala.collection.immutable.Seq<Object>> rowValues) {
        final List<Object> result = new ArrayList<>();
        for (final scala.collection.immutable.Seq<Object> value : rowValues) {
            result.addAll(ScalaCollections.javaList(value));
        }
        return result;
    }

    private static scala.collection.immutable.Seq<Object> getValue(final GlobalFieldSpecification spec,
            final ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> consumerRecord) {
        if (spec instanceof KeySpecification) {
            return getFromRecord(consumerRecord.key(), (FieldSpecification) spec);
        } else if (spec instanceof ValueSpecification) {
            return getFromRecord(consumerRecord.value(), (FieldSpecification) spec);
        } else if (spec == FieldSpecificationSingletons.timestampField()) {
            return ScalaCollections.seqOf(consumerRecord.timestamp());
        }
        throw new KafkaConnectorException(ExaError.messageBuilder("F-KCE-11")
                .message("Unsupported field specification {{SPECIFICATION}} is provided.", spec)
                .ticketMitigation()
                .toString());
    }

    private static scala.collection.immutable.Seq<Object> getFromRecord(
            final scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> recordPart,
            final FieldSpecification spec) {
        if (recordPart != null) {
            final Map<FieldSpecification, scala.collection.immutable.Seq<Object>> values = ScalaCollections.javaMap(recordPart);
            if (values.containsKey(spec)) {
                return values.get(spec);
            }
        }
        return defaultFor(spec);
    }

    private static scala.collection.immutable.Seq<Object> defaultFor(final FieldSpecification spec) {
        if (spec instanceof AllFieldsSpecification) {
            return null;
        }
        return ScalaCollections.seqOf((Object) null);
    }
}
