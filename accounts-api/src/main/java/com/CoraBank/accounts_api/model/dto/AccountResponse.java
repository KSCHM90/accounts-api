package com.corabank.accounts_api.model.dto;

import lombok.Builder;

@Builder
public record AccountResponse(
    Long id,
    String name,
    String cpf,
    Double balance,
    Boolean active
) {}
