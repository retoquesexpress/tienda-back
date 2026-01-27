package com.fpmislata.tienda_back.persistence.dao.jpa.impl;

import com.fpmislata.tienda_back.persistence.TestConfig;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.UserJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration(classes = TestConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
class ServiceJpaDaoImplTest {

    @Autowired
    private ServiceJpaDao serviceJpaDao;

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    @DisplayName("Test findAll services")
    void testFindAll() {
        List<ServiceJpaEntity> services = serviceJpaDao.findAll();
        assertNotNull(services);
        assertFalse(services.isEmpty());

    }

    @Test
    @DisplayName("Test findById")
    void testGetById() {
        // Create a category first
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        
        ServiceJpaEntity service1 = new ServiceJpaEntity(null, "Service 1", "Description 1", 100.0, "http://example.com/pic1.jpg", category);
        ServiceJpaEntity inserted = serviceJpaDao.create(service1);
        Integer generatedId = inserted.getIdService();

        Optional<ServiceJpaEntity> actual = serviceJpaDao.findById(generatedId);

        assertAll(
                () -> assertTrue(actual.isPresent()),
                () -> assertEquals(generatedId, actual.get().getIdService()),
                () -> assertEquals(inserted.getName(), actual.get().getName()),
                () -> assertEquals(inserted.getDescription(), actual.get().getDescription()),
                () -> assertEquals(inserted.getPrice(), actual.get().getPrice()),
                () -> assertEquals(inserted.getPictureUrl(), actual.get().getPictureUrl())
        );

    }

    @Test
    @DisplayName("Test findByCategoryId")
    void testFindByCategoryId() {
        Integer categoryId = 1;
        List<ServiceJpaEntity> services = serviceJpaDao.findByCategoryId(categoryId);
        assertNotNull(services);
        for (ServiceJpaEntity service : services) {
            assertNotNull(service.getCategory());
            assertEquals(categoryId, service.getCategory().getIdCategory());

        }
    }

    @Test
    @DisplayName("Test getById")
    void testGetByIdExisting() {
        // Create a category first
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        
        ServiceJpaEntity service1 = new ServiceJpaEntity(null, "Service 1", "Description 1", 100.0, "http://example.com/pic1.jpg", category);
        ServiceJpaEntity inserted = serviceJpaDao.create(service1);
        ServiceJpaEntity found = serviceJpaDao.getById(inserted.getIdService());

        assertAll(
                () -> assertNotNull(found),
                () -> assertEquals(inserted.getIdService(), found.getIdService()),
                () -> assertEquals(inserted.getName(), found.getName()),
                () -> assertEquals(inserted.getDescription(), found.getDescription()),
                () -> assertEquals(inserted.getPrice(), found.getPrice()),
                () -> assertEquals(inserted.getPictureUrl(), found.getPictureUrl())
        );
    }

    @Test
    @DisplayName("Test update service")
    void testUpdate() {
        // Create a category first
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        
        ServiceJpaEntity service = new ServiceJpaEntity(null, "Service 1", "Description 1", 100.0, "http://example.com/pic1.jpg", category);
        ServiceJpaEntity inserted = serviceJpaDao.create(service);

        inserted.setName("Updated Service");
        inserted.setDescription("Updated Description");
        inserted.setPrice(150.0);
        inserted.setPictureUrl("http://example.com/updated_pic1.jpg");

        ServiceJpaEntity updated = serviceJpaDao.update(inserted);

        assertAll(
                () -> assertEquals(inserted.getIdService(), updated.getIdService()),
                () -> assertEquals("Updated Service", updated.getName()),
                () -> assertEquals("Updated Description", updated.getDescription()),
                () -> assertEquals(150.0, updated.getPrice()),
                () -> assertEquals("http://example.com/updated_pic1.jpg", updated.getPictureUrl())
        );
    }



    @Test
    @DisplayName("Test delete service by id")
    void testDeleteById() {
        // Create a category first
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        
        ServiceJpaEntity service = new ServiceJpaEntity();
        service.setName("Service to Delete");
        service.setDescription("Description to Delete");
        service.setPrice(200.0);
        service.setPictureUrl("http://example.com/delete.jpg");
        service.setCategory(category);

        Long totalServicesBefore = (long) serviceJpaDao.findAll().size();

        ServiceJpaEntity inserted = serviceJpaDao.create(service);
        Integer generatedId = inserted.getIdService();

        serviceJpaDao.deleteById(generatedId);

        Long totalServicesAfter = (long) serviceJpaDao.findAll().size();

        assertEquals(totalServicesBefore, totalServicesAfter);
    }

    @Test
    @DisplayName("Test create service")
    void testCreateService() {
        // Create a category first
        CategoryJpaEntity category = entityManager.find(CategoryJpaEntity.class, 1);
        
        ServiceJpaEntity service = new ServiceJpaEntity(null, "Service 1", "Description 1", 100.0, "http://example.com/pic1.jpg", category);
        ServiceJpaEntity created = serviceJpaDao.create(service);

        assertNotNull(created.getIdService());
        assertEquals("Service 1", created.getName());
        assertEquals("Description 1", created.getDescription());
        assertEquals(100.0, created.getPrice());
        assertEquals("http://example.com/pic1.jpg", created.getPictureUrl());
    }


}

