package com.user_api.dto;

public record UserRequestDTO(
    String name,
    String email,
    String password,
    String cpf
) {
}
