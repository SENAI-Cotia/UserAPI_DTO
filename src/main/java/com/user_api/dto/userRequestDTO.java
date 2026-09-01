package com.user_api.dto;

public record userRequestDTO (
    Long id,
    String name,
    String email,
    String password,
    String cpf
) {
}
