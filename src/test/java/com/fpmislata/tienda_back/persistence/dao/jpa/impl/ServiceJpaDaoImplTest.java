package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Nested;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = {TestConfig.class})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ServiceJpaDaoImplTest {

    @PersistenceContext
    private EntityManager entityManager;
    @Autowired
    private ServiceJpaDaoImpl serviceJpaDao;

    @Nested
    class findAll {}
    @Nested
    class getById {}
    @Nested
    class findById {}
    @Nested
    class update {}
    @Nested
    class create {}
    @Nested
    class deleteById {}





}