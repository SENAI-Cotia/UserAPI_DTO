package com.user_api.mapper;

import com.user_api.dto.UserResponseDto;
import com.user_api.entity.User;

public class UserMapper {
    public static UserResponseDto toResponseDto(User user){
        return new UserResponseDto(
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getActive()
        );
    }
}
