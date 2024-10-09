package com.loanvortex.userservice.service;

import com.loanvortex.userservice.dto.UserRequestDTO;
import com.loanvortex.userservice.dto.UserResponseDTO;
import com.loanvortex.userservice.model.User;

public interface UserService {

    Iterable<UserResponseDTO> getAllUsers();

    UserResponseDTO getUserById(long id);

    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);

    UserResponseDTO editUser(UserRequestDTO userRequestDTO,Long userId);
}
