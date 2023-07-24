package com.exasol.cloudetl.kafka

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

/**
 * A Schema Registry container that exposes a fixed port.
 */
class SchemaRegistryContainer(image: DockerImageName, fixedPort: Int = 8081) extends GenericContainer(image) {

  override def configure(): Unit = {
    super.configure()
    addFixedExposedPort(fixedPort, fixedPort)
  }

}
