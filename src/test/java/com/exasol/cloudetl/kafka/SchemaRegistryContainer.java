package com.exasol.cloudetl.kafka;

import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;

class SchemaRegistryContainer extends GenericContainer<SchemaRegistryContainer> {
    private final int fixedPort;

    SchemaRegistryContainer(final DockerImageName image, final int fixedPort) {
        super(image);
        this.fixedPort = fixedPort;
    }

    @Override
    protected void configure() {
        super.configure();
        addFixedExposedPort(this.fixedPort, this.fixedPort);
    }
}
