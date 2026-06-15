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
            final ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> record,
            final int outputColumnCount) {
        final List<scala.collection.immutable.Seq<Object>> rowValues = new ArrayList<>();
        final List<Boolean> present = new ArrayList<>();
        for (final GlobalFieldSpecification spec : ScalaCollections.javaList(fieldSpecs)) {
            final scala.collection.immutable.Seq<Object> value = getValue(spec, record);
            rowValues.add(value);
            present.add(value != null);
        }
        final long absentCount = present.stream().filter(value -> !value).count();
        if (absentCount > 0) {
            if (absentCount > 1) {
                throw new KafkaConnectorException(ExaError.messageBuilder("E-KCE-12")
                        .message("Found multiple expressions that can be null.")
                        .mitigation("Please check that there is only single specification, e.g, key.* or value.*, "
                                + "that can map to multiple null columns.")
                        .toString());
            }
            int presentColumnCount = 0;
            for (int index = 0; index < rowValues.size(); index++) {
                if (present.get(index)) {
                    presentColumnCount += ScalaCollections.javaList(rowValues.get(index)).size();
                }
            }
            final int valuesMissing = outputColumnCount - presentColumnCount;
            final List<Object> result = new ArrayList<>();
            for (int index = 0; index < rowValues.size(); index++) {
                if (present.get(index)) {
                    result.addAll(ScalaCollections.javaList(rowValues.get(index)));
                } else {
                    for (int count = 0; count < valuesMissing; count++) {
                        result.add(null);
                    }
                }
            }
            return ScalaCollections.seq(result);
        }
        final List<Object> result = new ArrayList<>();
        for (final scala.collection.immutable.Seq<Object> value : rowValues) {
            result.addAll(ScalaCollections.javaList(value));
        }
        return ScalaCollections.seq(result);
    }

    private static scala.collection.immutable.Seq<Object> getValue(final GlobalFieldSpecification spec,
            final ConsumerRecord<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>, scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> record) {
        if (spec instanceof KeySpecification) {
            return getFromRecord(record.key(), (FieldSpecification) spec);
        } else if (spec instanceof ValueSpecification) {
            return getFromRecord(record.value(), (FieldSpecification) spec);
        } else if (spec == FieldSpecificationSingletons.timestampField()) {
            return ScalaCollections.seqOf(record.timestamp());
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
