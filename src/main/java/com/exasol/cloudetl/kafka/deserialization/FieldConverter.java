package com.exasol.cloudetl.kafka.deserialization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.exasol.cloudetl.kafka.ScalaCollections;

public class FieldConverter {
    private final scala.collection.immutable.Seq<Class<?>> outputColumnTypes;

    public FieldConverter(final scala.collection.immutable.Seq<Class<?>> outputColumnTypes) {
        this.outputColumnTypes = outputColumnTypes;
    }

    public scala.collection.immutable.Seq<Object> convertRow(final scala.collection.immutable.Seq<Object> row) {
        final List<Object> rowValues = ScalaCollections.javaList(row);
        final List<Class<?>> columnTypes = ScalaCollections.javaList(this.outputColumnTypes);
        final List<Object> result = new ArrayList<>();
        for (int index = 0; index < rowValues.size(); index++) {
            result.add(convert(columnTypes.get(index), rowValues.get(index)));
        }
        return ScalaCollections.seq(result);
    }

    public Object convert(final Class<?> columnType, final Object value) {
        if ((value instanceof Long) && (columnType == Timestamp.class)) {
            return new Timestamp((Long) value);
        }
        return value;
    }
}
