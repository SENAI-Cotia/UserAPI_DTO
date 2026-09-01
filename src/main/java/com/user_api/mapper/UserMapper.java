package com.user_api.mapper;

import com.user_api.dto.UserRequestDTO;
import com.user_api.dto.UserResponseDTO;
import com.user_api.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static UserResponseDTO toDtoResponse(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getActive()
        );
    }

    public static User toDtoRequest(UserRequestDTO userRequestDTO){
        User novoUser = new User();

        novoUser.setName(userRequestDTO.name());
        novoUser.setEmail(userRequestDTO.email());
        novoUser.setPassword(userRequestDTO.password());
        novoUser.setCpf(userRequestDTO.cpf());
        novoUser.setActive(true);
        novoUser.setCreatedAt(LocalDateTime.now());

        return novoUser;
    }
}
