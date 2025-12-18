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
    
    @GetMapping("/{idUser}")
    public ResponseEntity<UserDetailResponse> getById(@PathVariable String idUser) {
        UserDto userDto = userService.getById(idUser);
        UserDetailResponse userDetailResponse = UserMapper.getInstance().fromUserDtoToUserDetailResponse(userDto);
        return new ResponseEntity<>(userDetailResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDetailResponse> create(@RequestBody UserInsertRequest userInsertRequest) {
        UserDto userDto = new UserDto(
                userInsertRequest.idUser(),
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

    @PutMapping("/{idUser}")
    public ResponseEntity<UserDetailResponse> update(@PathVariable String idUser,
                                                        @RequestBody UserInsertRequest userInsertRequest) {

        UserDto userDto = new UserDto(
                idUser,
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

    @DeleteMapping("/{idUser}")
    public ResponseEntity<Void> delete(@PathVariable String idUser) {
        userService.delete(idUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
