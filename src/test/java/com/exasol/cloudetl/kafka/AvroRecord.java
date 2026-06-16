package com.exasol.cloudetl.kafka;

import org.apache.avro.*;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.avro.util.Utf8;

public final class AvroRecord extends SpecificRecordBase {
    private static final long serialVersionUID = 1L;
    static final Schema SCHEMA = new Schema.Parser().parse("{"
            + "\"namespace\":\"com.exasol.cloudetl\","
            + "\"type\":\"record\","
            + "\"name\":\"AvroRecordSchemaForIT\","
            + "\"fields\":["
            + "{\"name\":\"col_str\",\"type\":\"string\"},"
            + "{\"name\":\"col_int\",\"type\":\"int\"},"
            + "{\"name\":\"col_long\",\"type\":\"long\"}"
            + "]}");

    private String colStr;
    private int colInt;
    private long colLong;

    public AvroRecord() {
        this("", 0, 0);
    }

    AvroRecord(final String colStr, final int colInt, final long colLong) {
        this.colStr = colStr;
        this.colInt = colInt;
        this.colLong = colLong;
    }

    @Override
    public Object get(final int index) {
        switch (index) {
        case 0:
            return this.colStr;
        case 1:
            return this.colInt;
        case 2:
            return this.colLong;
        default:
            throw new AvroRuntimeException("Unknown index " + index + "!");
        }
    }

    @Override
    public void put(final int index, final Object value) {
        switch (index) {
        case 0:
            this.colStr = value instanceof Utf8 ? value.toString() : (String) value;
            break;
        case 1:
            this.colInt = (Integer) value;
            break;
        case 2:
            this.colLong = (Long) value;
            break;
        default:
            throw new AvroRuntimeException("Unknown index " + index + "!");
        }
    }

    @Override
    public Schema getSchema() {
        return SCHEMA;
    }
}
