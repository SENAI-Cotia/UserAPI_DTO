package com.user_api.mapper;

import com.user_api.dto.UserResponseDTO;
import com.user_api.entity.User;

public class UserMapper {

    public static UserResponseDTO toResponseDTO(User user){
        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getActive()
        );
    }
}
