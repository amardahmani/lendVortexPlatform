package com.loanstorm.user_service;

import com.loanvortex.userservice.controller.UserController;
import com.loanvortex.userservice.model.User;
import com.loanvortex.userservice.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllUsersReturnsListWithStatus200() {
        List<User> mockUsers = Arrays.asList(
                new User(1L, "user1", "password1", "user1@example.com", "USER", "First1", "Last1", "2023-01-01", "2023-01-01"),
                new User(2L, "user2", "password2", "user2@example.com", "USER", "First2", "Last2", "2023-01-01", "2023-01-01")
        );

        when(userService.getAllUsers()).thenReturn(mockUsers);

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockUsers, response.getBody());
    }

    @Test
    public void testGetAllUsersHandlesEmptyList() {
        when(userService.getAllUsers()).thenReturn(List.of());

        ResponseEntity<List<User>> response = userController.getAllUsers();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().isEmpty());
    }

    @Test
    public void testRetrieveUserByValidIdReturnsHttp200() {
        User user = new User(1L, "username", "password", "email@example.com", "role", "firstName", "lastName", "createdAt", "updatedAt");
        when(userService.getUserById(1L)).thenReturn(user);

        ResponseEntity<User> response = userController.getUserById(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testRetrieveUserByNonExistentIdReturnsHttp404() {
        when(userService.getUserById(999L)).thenReturn(null);

        ResponseEntity<User> response = userController.getUserById(999L);


        assertNull(response.getBody());
    }

    @Test
    public void testCreateUserWithValidData() {
        User user = User.builder()
                .username("testuser")
                .password("password")
                .email("testuser@example.com")
                .role("USER")
                .createdAt("2023-10-01")
                .updatedAt("2023-10-01")
                .build();

        User savedUser = User.builder()
                .id(1L)
                .username("testuser")
                .password("password")
                .email("testuser@example.com")
                .role("USER")
                .createdAt("2023-10-01")
                .updatedAt("2023-10-01")
                .build();

        when(userService.saveUser(user)).thenReturn(savedUser);

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    public void testCreateUserWithMissingFields() {
        User user = User.builder()
                .username(null) // Missing username
                .password("password")
                .email("testuser@example.com")
                .role("USER")
                .createdAt("2023-10-01")
                .updatedAt("2023-10-01")
                .build();

        when(userService.saveUser(user)).thenThrow(new IllegalArgumentException("Missing required fields"));

        ResponseEntity<User> response = userController.createUser(user);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testUpdateUserSuccess() {
        Long userId = 1L;
        User updatedUser = new User(null, "newUsername", "newPassword", "newEmail@example.com", "USER", "New", "User", "2023-01-01", "2023-01-02");
        User savedUser = new User(userId, "newUsername", "newPassword", "newEmail@example.com", "USER", "New", "User", "2023-01-01", "2023-01-02");

        when(userService.editUser(any(User.class))).thenReturn(savedUser);

        ResponseEntity<User> response = userController.updateUser(userId, updatedUser);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
        verify(userService).editUser(argThat(user -> user.getId().equals(userId)));
    }

    @Test
    public void testUpdateUserNotFound() {
        Long userId = 999L;
        User updatedUser = new User(null, "newUsername", "newPassword", "newEmail@example.com", "USER", "New", "User", "2023-01-01", "2023-01-02");

        when(userService.editUser(any(User.class))).thenThrow(new RuntimeException("User not found with id " + userId));

        ResponseEntity<User> response = userController.updateUser(userId, updatedUser);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
        verify(userService).editUser(argThat(user -> user.getId().equals(userId)));
    }
}