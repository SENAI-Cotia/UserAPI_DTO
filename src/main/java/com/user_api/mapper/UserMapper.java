package com.user_api.mapper;

import com.user_api.dto.UserRequestDTO;
import com.user_api.dto.UserResponseDTO;
import com.user_api.entity.User;

import java.time.LocalDateTime;

public class UserMapper {

    public static UserResponseDTO toResponseDTO(User user){

    return new UserResponseDTO(user.getId(), user.getName(), user.getEmail(), user.getCreatedAt(), user.getActive());
    }

    public static User toRequestDTO(UserRequestDTO user){
        User userExist = new User();

        userExist.setName(user.name());
        userExist.setEmail(user.email());
        userExist.setPassword(user.password());
        userExist.setCreatedAt(LocalDateTime.now());
        userExist.setActive(true);

        return userExist;
    }
}
