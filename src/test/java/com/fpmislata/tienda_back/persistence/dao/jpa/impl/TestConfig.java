package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@TestConfiguration
@EnableJpaRepositories(basePackages = "com.fpmislata.tienda_back.persistence.dao.jpa")
@EntityScan(basePackages = "com.fpmislata.tienda_back.persistence.dao.jpa.entity")
public class TestConfig {
    @Bean
    public ServiceJpaDaoImpl serviceJpaDao() {
        return new ServiceJpaDaoImpl();
    }
    @Bean
    public CategoryJpaDaoImpl categoryJpaDao() {
        return new CategoryJpaDaoImpl();
    }
    @Bean
    public UserJpaDaoImpl userJpaDao() {
        return new UserJpaDaoImpl();
    }
}
