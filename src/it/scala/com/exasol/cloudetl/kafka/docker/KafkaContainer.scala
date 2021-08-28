package com.exasol.cloudetl.kafka

import org.testcontainers.containers.GenericContainer
import org.testcontainers.utility.DockerImageName

/**
 * A Kafka container that exposes a fixed port.
 *
 * This is required since [[GenericContainer]] does not provide public API for exposing fixed ports.
 */
class KafkaContainer(image: DockerImageName, fixedPort: Int = 29092) extends GenericContainer(image) {

  override def configure(): Unit = {
    super.configure()
    addFixedExposedPort(fixedPort, fixedPort)
  }

}
