package com.user_api.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

public record UserRequestDTO(
        String name,

        String email,

        String password,

        String cpf
) {
}
