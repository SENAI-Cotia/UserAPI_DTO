package com.user_api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

/**
* DTO de entrada usado pelo cliente para cadastrar um usuario.
  *
  * <p>Propositalmente NAO possui os campos {@code id}, {@code createdAt} e
  * {@code active}: esses valores sao controlados exclusivamente pelo
  * servidor, entao o cliente nunca consegue defini-los ou sobrescreve-los
  * via requisicao.</p>
  */
public record UserRequestDTO(
  @NotBlank(message = "Nome e obrigatorio")
  String name,

  @NotBlank(message = "Email e obrigatorio")
  @Email(message = "Email invalido")
  String email,

  @NotBlank(message = "Senha e obrigatoria")
  String password,

  @NotBlank(message = "CPF e obrigatorio")
  @Pattern(regexp = "\\d{11}", message = "CPF deve conter 11 digitos numericos")
  String cpf
  ) {
}
