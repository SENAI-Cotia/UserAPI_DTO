package com.user_api.mapper;

import com.user_api.dto.UserRequestDTO;
import com.user_api.dto.UserResponseDTO;
import com.user_api.entity.User;

import java.time.LocalDateTime;
import java.util.List;

public class UserMapper {

    public static UserResponseDTO toResponseDTO(User user){

        return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getActive());
    }

    public static User toRequestDTO(UserRequestDTO user){
        User newUser = new User();


        newUser.setCpf(user.cpf());
        newUser.setEmail(user.email());
        newUser.setPassword(user.password());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setActive(true);

        return newUser;

    }

}
