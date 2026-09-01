package com.user_api.dto;

import java.time.LocalDateTime;

public record userResponseDTO (
        Long id,
        String name,
        String email,
        LocalDateTime createAt,
        Boolean active
){
}
