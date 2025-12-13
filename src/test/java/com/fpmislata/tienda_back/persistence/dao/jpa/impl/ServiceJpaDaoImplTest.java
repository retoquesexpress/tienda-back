package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

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
    class findAll {

        @BeforeEach
        @Transactional
        void setup() {
            // 1. Preparación: Insertar dos entidades de prueba en la base de datos
            ServiceJpaEntity service1 = new ServiceJpaEntity("S001", "Corte de pelo", "Descripción 1", 20.0, "url1");
            ServiceJpaEntity service2 = new ServiceJpaEntity("S002", "Manicura", "Descripción 2", 35.0, "url2");
            entityManager.persist(service1);
            entityManager.persist(service2);
            entityManager.flush(); // Asegurar que los datos se escriben antes del test
        }

        @Test
        @DisplayName("Debe devolver una lista con todos los servicios guardados")
        void shouldReturnAllServices() {
            // 2. Ejecución
            List<ServiceJpaEntity> result = serviceJpaDao.findAll();

            // 3. Verificación
            assertNotNull(result, "La lista no debe ser nula");
            assertEquals(2, result.size(), "Debe haber dos servicios en la lista");
            // Se puede verificar más, como el contenido de los objetos, si es necesario.
        }

        @Test
        @DisplayName("Debe devolver una lista vacía si no hay servicios")
        @Transactional
            // Necesario para limpiar la base de datos antes de este test
        void shouldReturnEmptyListWhenNoServicesExist() {
            // 1. Preparación: Limpiar la base de datos para asegurar que no hay servicios
            entityManager.createQuery("DELETE FROM ServiceJpaEntity").executeUpdate();
            entityManager.flush();

            // 2. Ejecución
            List<ServiceJpaEntity> result = serviceJpaDao.findAll();

            // 3. Verificación
            assertNotNull(result, "La lista no debe ser nula");
            assertTrue(result.isEmpty(), "La lista debe estar vacía");
        }
    }
    @Nested
    class getById {
        final String TEST_ID = "G001";
        final String NON_EXISTENT_ID = "G999";

        @BeforeEach
        @Transactional
        void setup() {
            // 1. Preparación: Insertar un servicio de prueba
            ServiceJpaEntity service = new ServiceJpaEntity(TEST_ID, "Masaje", "Masaje relajante", 60.0, "url_masaje");
            entityManager.persist(service);
            entityManager.flush();
        }

        @Test
        @DisplayName("Debe devolver el servicio cuando el ID existe")
        void shouldReturnServiceWhenIdExists() {
            // 2. Ejecución
            ServiceJpaEntity result = serviceJpaDao.getById(TEST_ID);

            // 3. Verificación
            assertNotNull(result, "El resultado no debe ser nulo");
            assertEquals(TEST_ID, result.getId_service());
            assertEquals("Masaje", result.getName());
        }

        @Test
        @DisplayName("Debe devolver null cuando el ID no existe")
        void shouldReturnNullWhenIdDoesNotExist() {
            // 2. Ejecución
            ServiceJpaEntity result = serviceJpaDao.getById(NON_EXISTENT_ID);

            // 3. Verificación
            assertNull(result, "El resultado debe ser nulo para un ID inexistente");
        }
    }
    @Nested
    class findById {
        final String TEST_ID = "F001";
        final String NON_EXISTENT_ID = "F999";

        @BeforeEach
        @Transactional
        void setup() {
            // 1. Preparación: Insertar un servicio de prueba
            ServiceJpaEntity service = new ServiceJpaEntity(TEST_ID, "Tratamiento Facial", "Limpieza profunda", 45.0, "url_facial");
            entityManager.persist(service);
            entityManager.flush();
        }

        @Test
        @DisplayName("Debe devolver un Optional con el servicio cuando el ID existe")
        void shouldReturnOptionalOfServiceWhenIdExists() {
            // 2. Ejecución
            Optional<ServiceJpaEntity> result = serviceJpaDao.findById(TEST_ID);

            // 3. Verificación
            assertTrue(result.isPresent(), "El Optional debe contener un valor");
            assertEquals(TEST_ID, result.get().getId_service());
        }

        @Test
        @DisplayName("Debe devolver un Optional vacío cuando el ID no existe")
        void shouldReturnEmptyOptionalWhenIdDoesNotExist() {
            // 2. Ejecución
            Optional<ServiceJpaEntity> result = serviceJpaDao.findById(NON_EXISTENT_ID);

            // 3. Verificación
            assertTrue(result.isEmpty(), "El Optional debe estar vacío");
        }
    }
    @Nested
    class update {
        final String TEST_ID = "U001";

        @BeforeEach
        @Transactional
        void setup() {
            // 1. Preparación: Insertar el servicio a actualizar
            ServiceJpaEntity service = new ServiceJpaEntity(TEST_ID, "Peluquería", "Corte y peinado", 30.0, "url_old");
            entityManager.persist(service);
            entityManager.flush();
            entityManager.clear(); // Limpiar el cache para forzar la carga desde la DB en la verificación
        }

        @Test
        @DisplayName("Debe actualizar el nombre y precio de un servicio existente")
        @Transactional
        void shouldUpdateExistingService() {
            // 1. Preparación: Crear la entidad con los nuevos valores
            ServiceJpaEntity serviceToUpdate = new ServiceJpaEntity(TEST_ID, "Peluquería Premium", "Corte, peinado y tinte", 75.0, "url_new");

            // 2. Ejecución
            ServiceJpaEntity result = serviceJpaDao.update(serviceToUpdate);

            // 3. Verificación
            assertNotNull(result, "El resultado no debe ser nulo");
            assertEquals(serviceToUpdate.getId_service(), result.getId_service());

            // Verificación en la base de datos
            ServiceJpaEntity updatedEntity = entityManager.find(ServiceJpaEntity.class, TEST_ID);

            assertEquals("Peluquería Premium", updatedEntity.getName());
            assertEquals(75.0, updatedEntity.getPrice());
            assertEquals("url_new", updatedEntity.getPictureUrl());
        }
    }
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
    class deleteById {
        final String TEST_ID = "D001";
        final String NON_EXISTENT_ID = "D999";

        @BeforeEach
        @Transactional
        void setup() {
            // 1. Preparación: Insertar el servicio a eliminar
            ServiceJpaEntity service = new ServiceJpaEntity(TEST_ID, "Limpieza dental", "Blanqueamiento", 150.0, "url_dental");
            entityManager.persist(service);
            entityManager.flush();
            entityManager.clear(); // Limpiar el cache
        }

        @Test
        @DisplayName("Debe eliminar un servicio existente por ID")
        @Transactional
        void shouldDeleteExistingServiceById() {
            // 2. Ejecución
            serviceJpaDao.deleteById(TEST_ID);

            // 3. Verificación
            ServiceJpaEntity deletedEntity = entityManager.find(ServiceJpaEntity.class, TEST_ID);

            assertNull(deletedEntity, "El servicio no debe ser encontrado después de la eliminación");
        }

        @Test
        @DisplayName("No debe fallar al intentar eliminar un ID inexistente")
        @Transactional
        void shouldNotFailWhenDeletingNonExistentId() {
            // 2. Ejecución
            assertDoesNotThrow(() -> serviceJpaDao.deleteById(NON_EXISTENT_ID));

            // 3. Verificación (Opcional, pero confirma que el servicio existente sigue ahí)
            assertNotNull(entityManager.find(ServiceJpaEntity.class, TEST_ID), "El servicio de prueba existente debe seguir ahí");
        }
    }
}