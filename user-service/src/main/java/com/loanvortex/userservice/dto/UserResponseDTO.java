package com.loanvortex.userservice.dto;

import lombok.Data;

@Data
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String customerId;
    private String createdAt;
    private String updatedAt;
}