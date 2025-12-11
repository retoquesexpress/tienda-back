package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.response.UserDetailResponse;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;


public class UserMapper {

    private static UserMapper instance;

    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (instance == null) {
            instance = new UserMapper();
        }
        return instance;
    }

    public UserDto fromUserJpaEntityToUserDto(UserJpaEntity userJpaEntity) {
        if (userJpaEntity == null) {
            return null;
        }
        return new UserDto(
                userJpaEntity.getId_user(),
                userJpaEntity.getName(),
                userJpaEntity.getEmail(),
                userJpaEntity.getUserName(),
                userJpaEntity.getPassword(),
                userJpaEntity.getPhoneNumber(),
                userJpaEntity.getAddress(),
                userJpaEntity.getBirthDate(),
                userJpaEntity.getRole()
        );
    }

    public UserJpaEntity fromUserDtoToUserJpaEntity(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new UserJpaEntity(
                userDto.id_user(),
                userDto.name(),
                userDto.email(),
                userDto.userName(),
                userDto.password(),
                userDto.phoneNumber(),
                userDto.address(),
                userDto.birthDate(),
                userDto.role()
        );
    }

    public static UserDetailResponse fromUserDtoToUserDetailResponse(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new UserDetailResponse(
                userDto.id_user(),
                userDto.name(),
                userDto.email(),
                userDto.userName(),
                userDto.phoneNumber(),
                userDto.address(),
                userDto.role(),
                userDto.birthDate(),
                userDto.password()

        );
    }

    public static UserDto fromUserDetailResponseToUserDto(UserDetailResponse userDetailResponse) {
        if (userDetailResponse == null) {
            return null;
        }
        return new UserDto(
                userDetailResponse.id_user(),
                userDetailResponse.name(),
                userDetailResponse.email(),
                userDetailResponse.userName(),
                userDetailResponse.phoneNumber(),
                userDetailResponse.address(),
                userDetailResponse.role(),
                userDetailResponse.birthDate(),
                userDetailResponse.password()
        );
    }

}

