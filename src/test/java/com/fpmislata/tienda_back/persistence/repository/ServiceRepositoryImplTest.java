package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.domain.service.dto.ServiceEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.ServiceJpaEntity;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ServiceRepositoryImplTest {

    @Mock
    private ServiceJpaDao serviceJpaDao;
    @InjectMocks
    private ServiceRepositoryImpl serviceRepositoryImpl;

    @Nested
    class FindAll {
        @Test
        @DisplayName("Test findAll should return list of services when services exist")
        void testFindAllShouldReturnListOfServicesWhenServicesExist() {
            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity("ca1", "Category 1");
            ServiceJpaEntity entity1 = new ServiceJpaEntity();
            entity1.setId_service("1");
            entity1.setName("Service 1");
            entity1.setDescription("Description 1");
            entity1.setPrice(100.0);
            entity1.setPictureUrl("img1.jpg");
            entity1.setCategory(categoryJpaEntity);

            ServiceJpaEntity entity2 = new ServiceJpaEntity();
            entity2.setId_service("2");
            entity2.setName("Service 2");
            entity2.setDescription("Description 2");
            entity2.setPrice(200.0);
            entity2.setPictureUrl("img2.jpg");
            entity2.setCategory(categoryJpaEntity);

            List<ServiceJpaEntity> mockEntities = List.of(entity1, entity2);

            when(serviceJpaDao.findAll()).thenReturn(mockEntities);

            List<ServiceEntity> result = serviceRepositoryImpl.findAll();
            assertThat(result.size() == 2);
        }

        @Test
        @DisplayName("Test findAll should return empty list when no services exist")
        void testFindAllShouldReturnEmptyListWhenNoServicesExist() {
            when(serviceJpaDao.findAll()).thenReturn(List.of());
            List<ServiceEntity> result = serviceRepositoryImpl.findAll();
            assertThat(result.isEmpty()).isTrue();
        }
    }

    @Nested
    class GetById {
        @Test
        @DisplayName("Test getById should return ServiceDto when service exists")
        void testGetByIdShouldReturnServiceWhenServiceExists() {
            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity("ca1", "Category 1");
            String serviceId = "1";
            ServiceJpaEntity entity = new ServiceJpaEntity();
            entity.setId_service(serviceId);
            entity.setName("Service 1");
            entity.setDescription("Description 1");
            entity.setPrice(100.0);
            entity.setPictureUrl("img1.jpg");
            entity.setCategory(categoryJpaEntity);

            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.of(entity));
            ServiceEntity result = serviceRepositoryImpl.getById(serviceId);
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("Test getById should return null when service does not exist")
        void testGetByIdShouldReturnNullWhenServiceDoesNotExist() {
            String serviceId = "nonexistent";
            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.empty());
            ServiceEntity result = serviceRepositoryImpl.getById(serviceId);
            assertThat(result).isNull();
        }
    }

    @Nested
    class FindById {
        @Test
        @DisplayName("Test findById should return ServiceDto when service exists")
        void testFindByIdShouldReturnServiceWhenServiceExists() {
            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity("ca1", "Category 1");
            String serviceId = "1";
            ServiceJpaEntity entity = new ServiceJpaEntity();
            entity.setId_service(serviceId);
            entity.setName("Service 1");
            entity.setDescription("Description 1");
            entity.setPrice(100.0);
            entity.setPictureUrl("img1.jpg");
            entity.setCategory(categoryJpaEntity);

            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.of(entity));
            Optional<ServiceEntity> result = serviceRepositoryImpl.findById(serviceId);
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("Test findById should return null when service does not exist")
        void testFindByIdShouldReturnNullWhenServiceDoesNotExist() {
            String serviceId = "nonexistent";
            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.empty());
            Optional<ServiceEntity> result = serviceRepositoryImpl.findById(serviceId);
            assertThat(result).isNotPresent();
        }
    }

    @Nested
    class Update {
        @Test
        @DisplayName("Test update should return updated ServiceDto")
        void testUpdateShouldReturnUpdatedService() {
            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity("ca1", "Category 1");
            String serviceId = "1";
            CategoryDto categoryDto = new CategoryDto("ca1", "Category 1");
            ServiceEntity serviceDto = new ServiceEntity(serviceId, "Updated Service", "Updated Description", 150.0, "updated_img.jpg", categoryDto);

            ServiceJpaEntity existingEntity = new ServiceJpaEntity();
            existingEntity.setId_service(serviceId);
            existingEntity.setName("Service 1");
            existingEntity.setDescription("Description 1");
            existingEntity.setPrice(100.0);
            existingEntity.setPictureUrl("img1.jpg");
            existingEntity.setCategory(categoryJpaEntity);

            ServiceJpaEntity updatedEntity = new ServiceJpaEntity();
            updatedEntity.setId_service(serviceId);
            updatedEntity.setName("Updated Service");
            updatedEntity.setDescription("Updated Description");
            updatedEntity.setPrice(150.0);
            updatedEntity.setPictureUrl("updated_img.jpg");
            updatedEntity.setCategory(categoryJpaEntity);

            when(serviceJpaDao.findById(serviceId)).thenReturn(Optional.of(existingEntity));
            when(serviceJpaDao.update(any(ServiceJpaEntity.class))).thenReturn(updatedEntity);

            ServiceEntity result = serviceRepositoryImpl.update(serviceDto);
            assertThat(result.name()).isEqualTo("Updated Service");
            assertThat(result.price()).isEqualTo(150.0);
        }

        @Test
        @DisplayName("Test update should throw EntityNotFoundException when service does not exist")
        void testUpdateShouldThrowEntityNotFoundExceptionWhenServiceDoesNotExist() {
            String serviceId = "nonexistent";
            CategoryDto categoryDto = new CategoryDto("ca1", "Category 1");
            ServiceEntity serviceDto = new ServiceEntity(serviceId, "Updated Service", "Updated Description", 150.0, "updated_img.jpg", categoryDto);

            when(serviceJpaDao.findById(serviceId)).thenReturn(Optional.empty());

            assertThrows(EntityNotFoundException.class, () -> {serviceRepositoryImpl.update(serviceDto);},"No Existe");
            verify(serviceJpaDao).findById(serviceId);
        }
    }

    @Nested
    class Create {
        @Test
        @DisplayName("Test create should return created ServiceDto")
        void testCreateShouldReturnCreatedService() {
            CategoryJpaEntity categoryJpaEntity = new CategoryJpaEntity("ca1", "Category 1");
            CategoryDto categoryDto = new CategoryDto("ca1", "Category 1");
            ServiceEntity serviceDto = new ServiceEntity("1", "New Service", "New Description", 120.0, "new_img.jpg", categoryDto);

            ServiceJpaEntity entityToCreate = new ServiceJpaEntity();
            entityToCreate.setId_service("1");
            entityToCreate.setName("New Service");
            entityToCreate.setDescription("New Description");
            entityToCreate.setPrice(120.0);
            entityToCreate.setPictureUrl("new_img.jpg");
            entityToCreate.setCategory(categoryJpaEntity);

            ServiceJpaEntity createdEntity = new ServiceJpaEntity();
            createdEntity.setId_service("1");
            createdEntity.setName("New Service");
            createdEntity.setDescription("New Description");
            createdEntity.setPrice(120.0);
            createdEntity.setPictureUrl("new_img.jpg");
            createdEntity.setCategory(categoryJpaEntity);

            when(serviceJpaDao.create(any(ServiceJpaEntity.class))).thenReturn(createdEntity);

            ServiceEntity result = serviceRepositoryImpl.create(serviceDto);
            assertThat(result).isNotNull();
            assertThat(result.name()).isEqualTo("New Service");
        }
    }

    @Nested
    class DeleteById {
        @Test
        @DisplayName("Test deleteById should call dao deleteById method")
        void testDeleteByIdShouldCallDaoDeleteByIdMethod() {
            String serviceId = "1";
            serviceRepositoryImpl.deleteById(serviceId);
            verify(serviceJpaDao).deleteById(serviceId);
        }
    }
}