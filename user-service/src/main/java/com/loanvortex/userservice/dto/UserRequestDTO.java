package com.loanvortex.userservice.dto;

import lombok.Data;

@Data
public class UserRequestDTO {

    private String username;
    private String password;
    private String email;
    private String role;
    private String firstName;
    private String lastName;
    private String customerId;
}
