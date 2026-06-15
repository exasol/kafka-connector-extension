package com.exasol.cloudetl.kafka.deserialization;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import com.exasol.cloudetl.kafka.ScalaCollections;
import com.exasol.common.avro.AvroConverter;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Deserializer;

public class GenericRecordDeserializer
        implements Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> {
    private final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs;
    private final Deserializer<GenericRecord> deserializer;
    private final AvroConverter converter = new AvroConverter();

    public GenericRecordDeserializer(final scala.collection.immutable.Seq<FieldSpecification> fieldSpecs,
            final Deserializer<GenericRecord> deserializer) {
        this.fieldSpecs = fieldSpecs;
        this.deserializer = deserializer;
    }

    @Override
    public scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>> deserialize(
            final String topic, final byte[] data) {
        final GenericRecord record = this.deserializer.deserialize(topic, data);
        final Schema recordSchema = record.getSchema();
        final Map<FieldSpecification, scala.collection.immutable.Seq<Object>> result = new LinkedHashMap<>();
        for (final FieldSpecification fieldSpec : ScalaCollections.javaList(this.fieldSpecs)) {
            if (fieldSpec instanceof AllFieldsSpecification) {
                final ArrayList<Object> values = new ArrayList<>();
                for (final Schema.Field recordField : recordSchema.getFields()) {
                    values.add(this.converter.convert(record.get(recordField.name()), recordField.schema()));
                }
                result.put(fieldSpec, ScalaCollections.seq(values));
            } else if (fieldSpec instanceof ConcreteField) {
                final Schema.Field field = recordSchema.getField(((ConcreteField) fieldSpec).fieldName());
                result.put(fieldSpec, ScalaCollections.seqOf(field == null ? null
                        : this.converter.convert(record.get(field.name()), field.schema())));
            } else if (fieldSpec instanceof FullRecord) {
                result.put(fieldSpec, ScalaCollections.seqOf(this.converter.convert(record, record.getSchema())));
            }
        }
        return ScalaCollections.immutableMap(result);
    }
}
