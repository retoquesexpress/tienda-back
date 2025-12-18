package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
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

    private UserEntity createUserDtoTest() {
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
            UserEntity userDto = createUserDtoTest();

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
//
//    @Nested class FromDtoToResponse {
//        @Test
//        @DisplayName("Mapeo Dto->Response")
//        void testFromDtoToResponseShouldMapAllFields() {
//            UserEntity userDto = createUserDtoTest();
//
//            var resultResponse = UserMapper.getInstance().fromUserDtoToUserDetailResponse(userDto);
//
//            assertThat(resultResponse).isNotNull();
//            assertThat(resultResponse.idUser()).isEqualTo(UserDto.id_User());
//            assertThat(resultResponse.name()).isEqualTo(UserDto.name())
//        }
//        @Test
//        @DisplayName("Mapeo Dto nulo->Response nulo")
//        void testFromDtoToResponseShouldReturnNullWhenInputIsNull() {
//            var resultResponse = UserMapper.getInstance().fromUserDtoToUserDetailResponse(null);
//            assertThat(resultResponse).isNull();
//        }
//    }

}