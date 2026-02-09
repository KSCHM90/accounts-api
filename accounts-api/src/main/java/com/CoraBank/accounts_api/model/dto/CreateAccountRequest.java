package com.corabank.accounts_api.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record CreateAccountRequest(
    @NotBlank(message = "Nome não pode ser vazio")
    @Size(min = 3, max = 100, message = "Nome deve ter entre 3 e 100 caracteres")
    String name,

    @NotBlank(message = "CPF não pode ser vazio")
    @Pattern(regexp = "^\\d{11}$", message = "CPF deve conter exatamente 11 dígitos")
    String cpf,

    @Pattern(regexp = "^$|CORA10", message = "Cupom fornecido inválido.")
    String referralCode
) {
}
