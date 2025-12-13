package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

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
    class create {
        final String new_id = "C001";

        @Test
        @DisplayName("Debe guardar un nuevo servicio en la base de datos")
        @Transactional
        void shouldCreateNewService() {
            ServiceJpaEntity newService = new ServiceJpaEntity(new_id, "Depilación", "Depilación láser", 50.0, "url_dep");
            ServiceJpaEntity createdEntity = serviceJpaDao.create(newService);

            assertNotNull(createdEntity, "La entidad creada no debe ser nula");
            assertEquals(new_id, createdEntity.getId_service());

            ServiceJpaEntity retrievedEntity = entityManager.find(ServiceJpaEntity.class, new_id);

            assertNotNull(retrievedEntity, "La entidad debe poder ser recuperada por el EntityManager");
            assertEquals("Depilación", retrievedEntity.getName());
            assertEquals(50.0, retrievedEntity.getPrice());
        }
    }
    @Nested
    class deleteById {}
}