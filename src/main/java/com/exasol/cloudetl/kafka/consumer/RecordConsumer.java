package com.exasol.cloudetl.kafka.consumer;

import com.exasol.ExaIterator;

public interface RecordConsumer {
    void emit(ExaIterator iterator);
}
