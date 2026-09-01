package com.user_api.dto;

import java.time.LocalDateTime;

public record UserResponseDto(

        String name,
        String email,
        LocalDateTime createdAt,
        Boolean active
) {
}
