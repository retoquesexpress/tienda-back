package com.fpmislata.tienda_back.persistence.repository;

import com.fpmislata.tienda_back.domain.service.dto.ServiceDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.ServiceJpaDao;
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
            ServiceJpaEntity entity1 = new ServiceJpaEntity();
            entity1.setId_service("1");
            entity1.setName("Service 1");
            entity1.setDescription("Description 1");
            entity1.setPrice(100.0);
            entity1.setPictureUrl("img1.jpg");

            ServiceJpaEntity entity2 = new ServiceJpaEntity();
            entity2.setId_service("2");
            entity2.setName("Service 2");
            entity2.setDescription("Description 2");
            entity2.setPrice(200.0);
            entity2.setPictureUrl("img2.jpg");

            List<ServiceJpaEntity> mockEntities = List.of(entity1, entity2);

            when(serviceJpaDao.findAll()).thenReturn(mockEntities);

            List<ServiceDto> result = serviceRepositoryImpl.findAll();
            assertThat(result.size() == 2);
        }

        @Test
        @DisplayName("Test findAll should return empty list when no services exist")
        void testFindAllShouldReturnEmptyListWhenNoServicesExist() {
            when(serviceJpaDao.findAll()).thenReturn(List.of());
            List<ServiceDto> result = serviceRepositoryImpl.findAll();
            assertThat(result.isEmpty()).isTrue();
        }
    }

    @Nested
    class GetById {
        @Test
        @DisplayName("Test getById should return ServiceDto when service exists")
        void testGetByIdShouldReturnServiceWhenServiceExists() {
            String serviceId = "1";
            ServiceJpaEntity entity = new ServiceJpaEntity();
            entity.setId_service(serviceId);
            entity.setName("Service 1");
            entity.setDescription("Description 1");
            entity.setPrice(100.0);
            entity.setPictureUrl("img1.jpg");

            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.of(entity));
            ServiceDto result = serviceRepositoryImpl.getById(serviceId);
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("Test getById should return null when service does not exist")
        void testGetByIdShouldReturnNullWhenServiceDoesNotExist() {
            String serviceId = "nonexistent";
            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.empty());
            ServiceDto result = serviceRepositoryImpl.getById(serviceId);
            assertThat(result).isNull();
        }
    }

    @Nested
    class FindById {
        @Test
        @DisplayName("Test findById should return ServiceDto when service exists")
        void testFindByIdShouldReturnServiceWhenServiceExists() {
            String serviceId = "1";
            ServiceJpaEntity entity = new ServiceJpaEntity();
            entity.setId_service(serviceId);
            entity.setName("Service 1");
            entity.setDescription("Description 1");
            entity.setPrice(100.0);
            entity.setPictureUrl("img1.jpg");

            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.of(entity));
            Optional<ServiceDto> result = serviceRepositoryImpl.findById(serviceId);
            assertThat(result).isNotNull();
        }

        @Test
        @DisplayName("Test findById should return null when service does not exist")
        void testFindByIdShouldReturnNullWhenServiceDoesNotExist() {
            String serviceId = "nonexistent";
            when(serviceJpaDao.findById(serviceId)).thenReturn(java.util.Optional.empty());
            Optional<ServiceDto> result = serviceRepositoryImpl.findById(serviceId);
            assertThat(result).isNotPresent();
        }
    }

    @Nested
    class Update {
        @Test
        @DisplayName("Test update should return updated ServiceDto")
        void testUpdateShouldReturnUpdatedService() {
            String serviceId = "1";
            ServiceDto serviceDto = new ServiceDto(serviceId, "Updated Service", "Updated Description", 150.0, "updated_img.jpg");

            ServiceJpaEntity existingEntity = new ServiceJpaEntity();
            existingEntity.setId_service(serviceId);
            existingEntity.setName("Service 1");
            existingEntity.setDescription("Description 1");
            existingEntity.setPrice(100.0);
            existingEntity.setPictureUrl("img1.jpg");

            ServiceJpaEntity updatedEntity = new ServiceJpaEntity();
            updatedEntity.setId_service(serviceId);
            updatedEntity.setName("Updated Service");
            updatedEntity.setDescription("Updated Description");
            updatedEntity.setPrice(150.0);
            updatedEntity.setPictureUrl("updated_img.jpg");

            when(serviceJpaDao.findById(serviceId)).thenReturn(Optional.of(existingEntity));
            when(serviceJpaDao.update(any(ServiceJpaEntity.class))).thenReturn(updatedEntity);

            ServiceDto result = serviceRepositoryImpl.update(serviceDto);
            assertThat(result.name()).isEqualTo("Updated Service");
            assertThat(result.price()).isEqualTo(150.0);
        }

        @Test
        @DisplayName("Test update should throw EntityNotFoundException when service does not exist")
        void testUpdateShouldThrowEntityNotFoundExceptionWhenServiceDoesNotExist() {
            String serviceId = "nonexistent";
            ServiceDto serviceDto = new ServiceDto(serviceId, "Updated Service", "Updated Description", 150.0, "updated_img.jpg");

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
            ServiceDto serviceDto = new ServiceDto("1", "New Service", "New Description", 120.0, "new_img.jpg");

            ServiceJpaEntity entityToCreate = new ServiceJpaEntity();
            entityToCreate.setId_service("1");
            entityToCreate.setName("New Service");
            entityToCreate.setDescription("New Description");
            entityToCreate.setPrice(120.0);
            entityToCreate.setPictureUrl("new_img.jpg");

            ServiceJpaEntity createdEntity = new ServiceJpaEntity();
            createdEntity.setId_service("1");
            createdEntity.setName("New Service");
            createdEntity.setDescription("New Description");
            createdEntity.setPrice(120.0);
            createdEntity.setPictureUrl("new_img.jpg");

            when(serviceJpaDao.create(any(ServiceJpaEntity.class))).thenReturn(createdEntity);

            ServiceDto result = serviceRepositoryImpl.create(serviceDto);
            assertThat(result).isNotNull();
            assertThat(result.name()).isEqualTo("New Service");
        }
        @Test
        @DisplayName("Test create should handle null fields appropriately")
        void testCreateShouldHandleNullFieldsAppropriately() {
            ServiceDto serviceDto = new ServiceDto("2", null, null, 0.0, null);

            ServiceJpaEntity entityToCreate = new ServiceJpaEntity();
            entityToCreate.setId_service("2");
            entityToCreate.setName(null);
            entityToCreate.setDescription(null);
            entityToCreate.setPrice(0.0);
            entityToCreate.setPictureUrl(null);

            ServiceJpaEntity createdEntity = new ServiceJpaEntity();
            createdEntity.setId_service("2");
            createdEntity.setName(null);
            createdEntity.setDescription(null);
            createdEntity.setPrice(0.0);
            createdEntity.setPictureUrl(null);

            when(serviceJpaDao.create(any(ServiceJpaEntity.class))).thenReturn(createdEntity);

            ServiceDto result = serviceRepositoryImpl.create(serviceDto);
            assertThat(result).isNotNull();
            assertThat(result.name()).isNull();
            assertThat(result.price()).isEqualTo(0.0);
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