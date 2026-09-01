package com.user_api.dto;

import java.time.LocalDateTime;

/**
* DTO de saida devolvido pela API.
  *
  * <p>Nao expoe {@code password} nem {@code cpf}: apenas dados que podem
  * ser retornados com seguranca ao cliente.</p>
  */
public record UserResponseDTO(
  Long id,
  String name,
  String email,
  LocalDateTime createdAt,
  Boolean active
  ) {
}
