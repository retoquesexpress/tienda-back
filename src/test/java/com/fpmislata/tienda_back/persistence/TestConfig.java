package com.fpmislata.tienda_back.persistence;

import com.fpmislata.tienda_back.persistence.dao.jpa.CategoryJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.impl.CategoryJpaDaoImpl;
import com.fpmislata.tienda_back.persistence.dao.jpa.impl.ServiceJpaDaoImpl;
import com.fpmislata.tienda_back.persistence.dao.jpa.impl.UserJpaDaoImpl;
import jakarta.persistence.EntityManager;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.fpmislata.tienda_back.persistence.dao.jpa")
@EntityScan(basePackages = "com.fpmislata.tienda_back.persistence.entity")
public class TestConfig {
    @Bean
    public UserJpaDao userJpaDao(EntityManager entityManager) {
        return new UserJpaDaoImpl();
    }

    @Bean
    public ServiceJpaDao serviceJpaDao(EntityManager entityManager) {
        return new ServiceJpaDaoImpl();
    }

    @Bean
    public CategoryJpaDao categoryJpaDao(EntityManager entityManager) {
        return new CategoryJpaDaoImpl();
    }
}
