package com.fpmislata.tienda_back.domain.service.impl;

import com.fpmislata.tienda_back.domain.model.User;
import com.fpmislata.tienda_back.domain.repository.UserRepository;
import com.fpmislata.tienda_back.domain.service.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Nested
    class TestsFindAllUsers {
        @Test
        @DisplayName("Test findAll should return list of users when users exist")
        void testFindAll_ShouldReturnListOfUsers_WhenUsersExist() {
            // Arrange
            List<User> expectedUsers = List.of(
                    new User("u1", "USER1", "user1@gamil.com", "user1","pass1", "123456789", "Address 1", null, "USER"),
                    new User("u2", "USER2", "user2@gamil.com", "user2","pass2", "123456459", "Address 2", null, "USER"),

            List< UserDto > expectedUserDto = expectedAuthors.stream()
                    .map(AuthorMapper.getInstance()::fromAuthorToAuthorDto).toList();

            when(authorRepository.findAll()).thenReturn(expectedAuthorsDto);

            // Act
            List<AuthorDto> actualAuthors = authorService.findAll();

            // Assert
            assertAll(
                    () -> assertEquals(expectedAuthorsDto.get(0).id(), actualAuthors.get(0).id()),
                    () -> assertEquals(expectedAuthorsDto.get(0).name(), actualAuthors.get(0).name()),
                    () -> assertEquals(expectedAuthorsDto.get(0).slug(), actualAuthors.get(0).slug()),
                    () -> assertEquals(expectedAuthorsDto.get(1).id(), actualAuthors.get(1).id()),
                    () -> assertEquals(expectedAuthorsDto.get(1).name(), actualAuthors.get(1).name()),
                    () -> assertEquals(expectedAuthorsDto.get(1).slug(), actualAuthors.get(1).slug()));
            verify(authorRepository, times(1)).findAll();
        }
    }
}