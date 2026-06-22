package com.exasol.cloudetl.kafka.deserialization;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class FieldConverter {
    private final List<Class<?>> outputColumnTypes;

    public FieldConverter(final List<Class<?>> outputColumnTypes) {
        this.outputColumnTypes = outputColumnTypes;
    }

    public List<Object> convertRow(final List<Object> row) {
        final List<Object> rowValues = row;
        final List<Class<?>> columnTypes = this.outputColumnTypes;
        final List<Object> result = new ArrayList<>();
        for (int index = 0; index < rowValues.size(); index++) {
            result.add(convert(columnTypes.get(index), rowValues.get(index)));
        }
        return result;
    }

    public Object convert(final Class<?> columnType, final Object value) {
        if ((value instanceof Long) && (columnType == Timestamp.class)) {
            return new Timestamp((Long) value);
        }
        return value;
    }
}
