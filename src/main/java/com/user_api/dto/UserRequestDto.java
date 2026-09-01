package com.user_api.dto;

public record UserRequestDto(
        String name,
        String email,
        String password,
        String cpf
) {
}
