package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.request.CategoryInsertRequest;
import com.fpmislata.tienda_back.domain.model.User;
import com.fpmislata.tienda_back.domain.repository.entity.CategoryEntity;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.dto.CategoryDto;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.CategoryJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


class UserMapperTest {
    private final String idPruebas= "u1";
    private final String namePruebas= "User Pruebas";
    private final String emailPruebas= "Description Pruebas";
    private final String usernamePruebas= "userPruebas";
    private final String passwordPruebas= "pswrdPruebas";
    private final String phoneNumberPruebas= "1234567890";
    private final String addressPruebas= "Address Pruebas";
    private final Date birthDatePruebas= new Date();
    private final String rolePruebas= "USER";


    private UserJpaEntity createUserJpaEntityTest() {
        return new UserJpaEntity(
                idPruebas
                ,namePruebas
                ,emailPruebas
                ,usernamePruebas
                ,passwordPruebas
                ,phoneNumberPruebas
                ,addressPruebas
                ,birthDatePruebas
                ,rolePruebas

        );
    }

    private UserDto createUserDtoTest() {
        return new UserDto(
                idPruebas,
                namePruebas,
                emailPruebas,
                usernamePruebas,
                passwordPruebas,
                phoneNumberPruebas,
                addressPruebas,
                birthDatePruebas,
                rolePruebas

        );
    }

    private UserEntity createUserEntityTest() {
        return new UserEntity(
                idPruebas,
                namePruebas,
                emailPruebas,
                usernamePruebas,
                passwordPruebas,
                phoneNumberPruebas,
                addressPruebas,
                birthDatePruebas,
                rolePruebas

        );
    }





    @Nested
    class FromJpaEntityToDto {
        @Test
        @DisplayName("Mapeo Entity->Dto")
        void testFromJpaEntityToDtoShouldMapAllFields() {

            UserJpaEntity jpaEntity = createUserJpaEntityTest();


            UserEntity resultDto = UserMapper.getInstance().fromUserJpaEntityToUserEntity(jpaEntity);

            assertThat(resultDto).isNotNull();
            assertThat(resultDto.idUser()).isEqualTo(jpaEntity.getIdUser());
            assertThat(resultDto.name()).isEqualTo(jpaEntity.getName());
            assertThat(resultDto.email()).isEqualTo(jpaEntity.getEmail());
            assertThat(resultDto.userName()).isEqualTo(jpaEntity.getUserName());
            assertThat(resultDto.password()).isEqualTo(jpaEntity.getPassword());
            assertThat(resultDto.phoneNumber()).isEqualTo(jpaEntity.getPhoneNumber());
            assertThat(resultDto.address()).isEqualTo(jpaEntity.getAddress());
            assertThat(resultDto.birthDate()).isEqualTo(jpaEntity.getBirthDate());
            assertThat(resultDto.role()).isEqualTo(jpaEntity.getRole());
        }


        @Test
        @DisplayName("Mapeo Entity nulo->Dto nulo")
        void testFromJpaEntityToDtoShouldReturnNullWhenInputIsNull() {
            UserEntity resultDto = UserMapper.getInstance().fromUserJpaEntityToUserEntity(null);
            assertThat(resultDto).isNull();
        }
    }

    @Nested class FromDtoToJpaEntity {
        @Test
        @DisplayName("Mapeo Dto->Entity")
        void testFromDtoToJpaEntityShouldMapAllFields() {
            UserEntity userDto = createUserEntityTest();

            UserJpaEntity resultEntity = UserMapper.getInstance().fromUserEntityToUserJpaEntity(userDto);

            assertThat(resultEntity).isNotNull();
            assertThat(resultEntity.getIdUser()).isEqualTo(userDto.idUser());
            assertThat(resultEntity.getName()).isEqualTo(userDto.name());
            assertThat(resultEntity.getEmail()).isEqualTo(userDto.email());
            assertThat(resultEntity.getUserName()).isEqualTo(userDto.userName());
            assertThat(resultEntity.getPassword()).isEqualTo(userDto.password());
            assertThat(resultEntity.getPhoneNumber()).isEqualTo(userDto.phoneNumber());
            assertThat(resultEntity.getAddress()).isEqualTo(userDto.address());
            assertThat(resultEntity.getBirthDate()).isEqualTo(userDto.birthDate());
            assertThat(resultEntity.getRole()).isEqualTo(userDto.role());
        }

        @Test
        @DisplayName("Mapeo Dto nulo->Entity nulo")
        void testFromDtoToJpaEntityShouldReturnNullWhenInputIsNull() {
            UserJpaEntity resultEntity = UserMapper.getInstance().fromUserEntityToUserJpaEntity(null);
            assertThat(resultEntity).isNull();
        }
    }

    @Nested
    class fromUserEntityToUserDto {
        @Test
        @DisplayName("Mapeo Entity->Dto")
        void testFromEntityToDtoShouldMapAllFields() {

            UserEntity userEntity = createUserEntityTest();


            UserDto result = UserMapper.getInstance().fromUserEntityToUserDto(userEntity);

            assertThat(result).isNotNull();
            assertThat(result.idUser()).isEqualTo(userEntity.idUser());
            assertThat(result.name()).isEqualTo(userEntity.name());
            assertThat(result.email()).isEqualTo(userEntity.email());
            assertThat(result.userName()).isEqualTo(userEntity.userName());
            assertThat(result.password()).isEqualTo(userEntity.password());
            assertThat(result.phoneNumber()).isEqualTo(userEntity.phoneNumber());
            assertThat(result.address()).isEqualTo(userEntity.address());
            assertThat(result.birthDate()).isEqualTo(userEntity.birthDate());
            assertThat(result.role()).isEqualTo(userEntity.role());
        }


        @Test
        @DisplayName("Mapeo Entity nulo->Dto nulo")
        void testFromEntityToDtoShouldReturnNullWhenInputIsNull() {
            UserDto result = UserMapper.getInstance().fromUserEntityToUserDto(null);
            assertThat(result).isNull();
        }
    }

    @Nested
    class fromUserDtoToUser {
        @Test
        @DisplayName("Mapeo Dto->User")
        void testFromDtoToUserShouldMapAllFields() {

            UserDto userDto = createUserDtoTest();


            User result = UserMapper.getInstance().fromUserDtoToUser(userDto);

            assertThat(result).isNotNull();
            assertThat(result.getId()).isEqualTo(userDto.idUser());
            assertThat(result.getName()).isEqualTo(userDto.name());
            assertThat(result.getEmail()).isEqualTo(userDto.email());
            assertThat(result.getUserName()).isEqualTo(userDto.userName());
            assertThat(result.getPassword()).isEqualTo(userDto.password());
            assertThat(result.getPhoneNumber()).isEqualTo(userDto.phoneNumber());
            assertThat(result.getAddress()).isEqualTo(userDto.address());
            assertThat(result.getBirthDate()).isEqualTo(userDto.birthDate());
            assertThat(result.getRole()).isEqualTo(userDto.role());
        }


        @Test
        @DisplayName("Mapeo InsertRequest nulo->Dto nulo")
        void testFromDtoToUserShouldReturnNullWhenInputIsNull() {
            User result = UserMapper.getInstance().fromUserDtoToUser(null);
            assertThat(result).isNull();
        }
    }

    @Nested
    class fromUserJpaEntityToUserEntity {
        @Test
        @DisplayName("Mapeo JpaEntity->Entity")
        void testFromUserJpaEntityToUserEntityShouldMapAllFields() {

            UserJpaEntity userJpaEntity = createUserJpaEntityTest();


            UserEntity result = UserMapper.getInstance().fromUserJpaEntityToUserEntity(userJpaEntity);

            assertThat(result).isNotNull();
            assertThat(result.idUser()).isEqualTo(userJpaEntity.getIdUser());
            assertThat(result.name()).isEqualTo(userJpaEntity.getName());
            assertThat(result.email()).isEqualTo(userJpaEntity.getEmail());
            assertThat(result.userName()).isEqualTo(userJpaEntity.getUserName());
            assertThat(result.password()).isEqualTo(userJpaEntity.getPassword());
            assertThat(result.phoneNumber()).isEqualTo(userJpaEntity.getPhoneNumber());
            assertThat(result.address()).isEqualTo(userJpaEntity.getAddress());
            assertThat(result.birthDate()).isEqualTo(userJpaEntity.getBirthDate());
            assertThat(result.role()).isEqualTo(userJpaEntity.getRole());
        }


        @Test
        @DisplayName("Mapeo JpaEntity nulo->Entity nulo")
        void testFromUserJpaEntityToUserEntityShouldReturnNullWhenInputIsNull() {
            UserEntity result = UserMapper.getInstance().fromUserJpaEntityToUserEntity(null);
            assertThat(result).isNull();
        }
    }

    @Nested
    class fromUserEntityToUserJpaEntity {
        @Test
        @DisplayName("Mapeo Entity->JpaEntity")
        void testFromUserEntityToUserJpaEntityShouldMapAllFields() {

            UserEntity userEntity = createUserEntityTest();


            UserJpaEntity result = UserMapper.getInstance().fromUserEntityToUserJpaEntity(userEntity);

            assertThat(result).isNotNull();
            assertThat(result.getIdUser()).isEqualTo(userEntity.idUser());
            assertThat(result.getName()).isEqualTo(userEntity.name());
            assertThat(result.getEmail()).isEqualTo(userEntity.email());
            assertThat(result.getUserName()).isEqualTo(userEntity.userName());
            assertThat(result.getPassword()).isEqualTo(userEntity.password());
            assertThat(result.getPhoneNumber()).isEqualTo(userEntity.phoneNumber());
            assertThat(result.getAddress()).isEqualTo(userEntity.address());
            assertThat(result.getBirthDate()).isEqualTo(userEntity.birthDate());
            assertThat(result.getRole()).isEqualTo(userEntity.role());
        }


        @Test
        @DisplayName("Mapeo Entity nulo->JpaEntity nulo")
        void testFromUserEntityToUserJpaEntityShouldReturnNullWhenInputIsNull() {
            UserJpaEntity result = UserMapper.getInstance().fromUserEntityToUserJpaEntity(null);
            assertThat(result).isNull();
        }
    }

}


