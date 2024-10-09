package com.loanvortex.userservice.mapper;

import com.loanvortex.userservice.dto.UserRequestDTO;
import com.loanvortex.userservice.dto.UserResponseDTO;
import com.loanvortex.userservice.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User toEntity(UserRequestDTO userRequestDTO);
    UserResponseDTO toResponse(User user);
}
