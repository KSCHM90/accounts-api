package com.CoraBank.accounts_api.DTO;

import lombok.Builder;

@Builder
public record AccountResponse(
    Long id,
    String name,
    String cpf
) {}
