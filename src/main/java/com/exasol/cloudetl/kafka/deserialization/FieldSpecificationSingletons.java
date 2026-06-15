package com.exasol.cloudetl.kafka.deserialization;

import java.lang.reflect.Field;

import org.apache.kafka.common.serialization.Deserializer;

final class FieldSpecificationSingletons {
    private static final String PACKAGE = "com.exasol.cloudetl.kafka.deserialization.";

    private FieldSpecificationSingletons() {
    }

    static GlobalFieldSpecification recordKey() {
        return singleton("RecordKey$");
    }

    static GlobalFieldSpecification recordValue() {
        return singleton("RecordValue$");
    }

    static GlobalFieldSpecification recordKeyFields() {
        return singleton("RecordKeyFields$");
    }

    static GlobalFieldSpecification recordValueFields() {
        return singleton("RecordValueFields$");
    }

    static GlobalFieldSpecification timestampField() {
        return singleton("TimestampField$");
    }

    @SuppressWarnings("unchecked")
    static Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>> ignoreKeyDeserializer() {
        return (Deserializer<scala.collection.immutable.Map<FieldSpecification, scala.collection.immutable.Seq<Object>>>) loadSingleton(
                "IgnoreKeyDeserializer$", Deserializer.class);
    }

    private static GlobalFieldSpecification singleton(final String className) {
        return loadSingleton(className, GlobalFieldSpecification.class);
    }

    private static <T> T loadSingleton(final String className, final Class<T> expectedType) {
        try {
            final Class<?> singletonClass = Class.forName(PACKAGE + className);
            final Field module = singletonClass.getField("MODULE$");
            return expectedType.cast(module.get(null));
        } catch (final ReflectiveOperationException exception) {
            throw new IllegalStateException("Failed to load Scala singleton " + className + ".", exception);
        }
    }
}
