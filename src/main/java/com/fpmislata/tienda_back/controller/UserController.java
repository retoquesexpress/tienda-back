package com.fpmislata.tienda_back.controller;

import com.fpmislata.tienda_back.controller.webModel.request.UserInsertRequest;
import com.fpmislata.tienda_back.controller.webModel.response.UserDetailResponse;
import com.fpmislata.tienda_back.domain.service.UserService;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import com.fpmislata.tienda_back.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserDetailResponse>findAll(){
        List<UserDetailResponse> userDetailResponse = userService.findAllUsers()
                .stream()
                .map(UserMapper.getInstance()::fromUserDtoToUserDetailResponse)
                .toList();
        return new ResponseEntity(userDetailResponse, HttpStatus.OK);
    }
    
    @GetMapping("/{id_user}")
    public ResponseEntity<UserDetailResponse> getById(@PathVariable String id_user) {
        UserDto userDto = userService.getById(id_user);
        UserDetailResponse userDetailResponse = UserMapper.getInstance().fromUserDtoToUserDetailResponse(userDto);
        return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDetailResponse> create(@RequestBody UserInsertRequest userInsertRequest) {
        UserDto userDto = new UserDto(
                userInsertRequest.id_user(),
                userInsertRequest.name(),
                userInsertRequest.email(),
                userInsertRequest.userName(),
                userInsertRequest.phoneNumber(),
                userInsertRequest.address(),
                userInsertRequest.role(),
                userInsertRequest.birthDate(),
                userInsertRequest.password()
        );

        UserDto createdUser = userService.create(userDto);
        UserDetailResponse createdUserResponse =
                UserMapper.getInstance().fromUserDtoToUserDetailResponse(createdUser);

        return new ResponseEntity<>(createdUserResponse, HttpStatus.CREATED);
    }

    @PutMapping("/{id_user}")
    public ResponseEntity<UserDetailResponse> update(@PathVariable String id_user,
                                                        @RequestBody UserInsertRequest userInsertRequest) {

        UserDto userDto = new UserDto(
                id_user,
                userInsertRequest.name(),
                userInsertRequest.email(),
                userInsertRequest.userName(),
                userInsertRequest.phoneNumber(),
                userInsertRequest.address(),
                userInsertRequest.role(),
                userInsertRequest.birthDate(),
                userInsertRequest.password()

        );

        UserDto updatedUser = userService.update(userDto);
        UserDetailResponse updatedUserResponse =
                UserMapper.getInstance().fromUserDtoToUserDetailResponse(updatedUser);

        return new ResponseEntity<>(updatedUserResponse, HttpStatus.OK);
    }

    @DeleteMapping("/{id_user}")
    public ResponseEntity<Void> delete(@PathVariable String id_user) {
        userService.delete(id_user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
