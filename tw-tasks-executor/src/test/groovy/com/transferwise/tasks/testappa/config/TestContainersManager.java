package com.transferwise.tasks.testappa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.testcontainers.containers.DockerComposeContainer;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Slf4j
public class TestContainersManager {
    @Autowired
    DockerComposeContainer dockerComposeContainer;

    private int registrationCount = 0;

    @PostConstruct
    public void init() {
        registrationCount++;
    }

    @PreDestroy
    public void destroy() {
        registrationCount--;
        if (registrationCount == 0) {
            log.info("Stopping docker compose container.");
            dockerComposeContainer.stop();
        }
    }
}
