package com.loanstorm.user_service;

import com.loanvortex.userservice.model.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    @Test
    public void testUserCreation() {
        User user = User.builder()
                .username("testuser")
                .password("password123")
                .email("testuser@example.com")
                .role("USER")
                .firstName("Test")
                .lastName("User")
                .createdAt(LocalDateTime.now().toString())
                .updatedAt(LocalDateTime.now().toString())
                .build();

        assertNotNull(user);
        assertEquals("testuser", user.getUsername());
        assertEquals("password123", user.getPassword());
        assertEquals("testuser@example.com", user.getEmail());
        assertEquals("USER", user.getRole());
        assertEquals("Test", user.getFirstName());
        assertEquals("User", user.getLastName());
    }

    @Test
    public void testUserBuilder() {
        User user = User.builder()
                .username("builderuser")
                .password("builderpassword")
                .email("builderuser@example.com")
                .role("ADMIN")
                .createdAt("2024-01-01T00:00:00Z")
                .updatedAt("2024-01-01T00:00:00Z")
                .build();

        assertEquals("builderuser", user.getUsername());
        assertEquals("builderpassword", user.getPassword());
        assertEquals("builderuser@example.com", user.getEmail());
        assertEquals("ADMIN", user.getRole());
        assertEquals("2024-01-01T00:00:00Z", user.getCreatedAt());
        assertEquals("2024-01-01T00:00:00Z", user.getUpdatedAt());
    }

    @Test
    public void testUserNullConstraints() {
        User user = User.builder()
                .username(null) // username should not be null
                .password("password")
                .email("user@example.com")
                .role("USER")
                .createdAt("2024-01-01T00:00:00Z")
                .updatedAt("2024-01-01T00:00:00Z")
                .build();

        assertThrows(NullPointerException.class, () -> {
            user.getUsername();
        });
    }


}
