package com.fpmislata.tienda_back.mapper;

import com.fpmislata.tienda_back.controller.webModel.response.UserDetailResponse;
import com.fpmislata.tienda_back.domain.model.User;
import com.fpmislata.tienda_back.domain.repository.entity.UserEntity;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.persistence.dao.jpa.entity.UserJpaEntity;


public class UserMapper {

    private static UserMapper INSTANCE;


    private UserMapper() {
    }

    public static UserMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new UserMapper();
        }
        return INSTANCE;
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

    public UserDetailResponse fromUserDtoToUserDetailResponse(UserDto userDto) {
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

    public UserDto fromUserDetailResponseToUserDto(UserDetailResponse userDetailResponse) {
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

    public UserDto fromUserEntityToUserDto(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserDto(
                userEntity.id_user(),
                userEntity.name(),
                userEntity.email(),
                userEntity.userName(),
                userEntity.password(),
                userEntity.phoneNumber(),
                userEntity.address(),
                userEntity.birthDate(),
                userEntity.role()
        );
    }

    public User fromUserDtoToUser(UserDto userDto) {
        if (userDto == null) {
            return null;
        }
        return new User(
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

    public UserEntity fromUserJpaEntityToUserEntity(UserJpaEntity entity) {
        if (entity == null) {
            return null;
        }
        return new UserEntity(
                entity.getId_user(),
                entity.getName(),
                entity.getEmail(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getPhoneNumber(),
                entity.getAddress(),
                entity.getBirthDate(),
                entity.getRole()
        );
    }

    public UserJpaEntity fromUserEntityToUserJpaEntity(UserEntity userEntity) {
        if (userEntity == null) {
            return null;
        }
        return new UserJpaEntity(
                userEntity.id_user(),
                userEntity.name(),
                userEntity.email(),
                userEntity.userName(),
                userEntity.password(),
                userEntity.phoneNumber(),
                userEntity.address(),
                userEntity.birthDate(),
                userEntity.role()
        );
    }

    public UserDto fromUserToUserDto( User user) {
        if (user == null) {
            return null;
        }
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getUserName(),
                user.getPassword(),
                user.getPhoneNumber(),
                user.getAddress(),
                user.getBirthDate(),
                user.getRole()
        );
    }
}

