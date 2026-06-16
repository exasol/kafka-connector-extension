package com.exasol.cloudetl.kafka;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

/**
 * A Kafka container that exposes a fixed port.
 */
class KafkaContainer extends GenericContainer<KafkaContainer> {
    private final int fixedPort;

    KafkaContainer(final DockerImageName image, final int fixedPort) {
        super(image);
        this.fixedPort = fixedPort;
    }

    @Override
    protected void configure() {
        super.configure();
        addFixedExposedPort(this.fixedPort, this.fixedPort);
    }
}
