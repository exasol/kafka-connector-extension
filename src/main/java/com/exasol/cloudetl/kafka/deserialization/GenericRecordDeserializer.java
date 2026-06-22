package com.exasol.cloudetl.kafka.deserialization;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.exasol.cloudetl.kafka.ScalaCollections;
import com.exasol.common.avro.AvroConverter;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;

public class GenericRecordDeserializer implements Deserializer<Map<FieldSpecification, List<Object>>> {
    private final List<FieldSpecification> fieldSpecs;
    private final Deserializer<GenericRecord> deserializer;
    private final AvroConverter converter = new AvroConverter();

    public GenericRecordDeserializer(final List<FieldSpecification> fieldSpecs,
            final Deserializer<GenericRecord> deserializer) {
        this.fieldSpecs = fieldSpecs;
        this.deserializer = deserializer;
    }

    @Deprecated
    public GenericRecordDeserializer(final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs,
            final Deserializer<GenericRecord> deserializer) {
        this(ScalaCollections.javaList(fieldSpecs), deserializer);
    }

    @Override
    public Map<FieldSpecification, List<Object>> deserialize(final String topic, final byte[] data) {
        final GenericRecord genericRecord = this.deserializer.deserialize(topic, data);
        final Schema recordSchema = genericRecord.getSchema();
        final Map<FieldSpecification, List<Object>> result = new LinkedHashMap<>();
        for (final FieldSpecification fieldSpec : this.fieldSpecs) {
            if (fieldSpec instanceof AllFieldsSpecification) {
                final List<Object> values = new ArrayList<>();
                for (final Schema.Field recordField : recordSchema.getFields()) {
                    values.add(this.converter.convert(genericRecord.get(recordField.name()), recordField.schema()));
                }
                result.put(fieldSpec, values);
            } else if (fieldSpec instanceof ConcreteField) {
                final Schema.Field field = recordSchema.getField(((ConcreteField) fieldSpec).fieldName());
                result.put(fieldSpec, Collections.singletonList(field == null ? null
                        : this.converter.convert(genericRecord.get(field.name()), field.schema())));
            } else if (fieldSpec instanceof FullRecord) {
                result.put(fieldSpec, Collections.singletonList(this.converter.convert(genericRecord, genericRecord.getSchema())));
            }
        }
        return result;
    }
}
