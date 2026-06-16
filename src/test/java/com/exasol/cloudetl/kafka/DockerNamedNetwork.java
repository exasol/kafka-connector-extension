package com.exasol.cloudetl.kafka;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.Network;

/**
 * A reusable docker network.
 */
class DockerNamedNetwork implements Network {
    private static final Logger LOGGER = Logger.getLogger(DockerNamedNetwork.class.getName());
    private static final ConcurrentHashMap<String, DockerNamedNetwork> NAMED_NETWORKS = new ConcurrentHashMap<>();

    private final String name;
    private final boolean reuse;
    private final String id;

    static DockerNamedNetwork get(final String name, final boolean reuse) {
        return NAMED_NETWORKS.computeIfAbsent(name, key -> new DockerNamedNetwork(key, reuse));
    }

    private DockerNamedNetwork(final String name, final boolean reuse) {
        this.name = name;
        this.reuse = reuse;
        this.id = getNetworkId();
    }

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void close() {
        if (this.reuse) {
            LOGGER.warning(() -> "Skipping the network termination because 'reuse' is enabled. Please destroy "
                    + "the network manually using 'docker network rm " + this.id + "'.");
        } else {
            DockerClientFactory.lazyClient().removeNetworkCmd(this.id).exec();
        }
    }

    private String getNetworkId() {
        return DockerClientFactory.lazyClient().listNetworksCmd().withNameFilter(this.name).exec().stream().findAny()
                .map(com.github.dockerjava.api.model.Network::getId).orElseGet(this::createNetwork);
    }

    private String createNetwork() {
        return DockerClientFactory.lazyClient().createNetworkCmd().withName(this.name).exec().getId();
    }
}
