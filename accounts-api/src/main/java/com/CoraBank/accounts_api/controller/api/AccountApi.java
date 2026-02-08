package com.corabank.accounts_api.controller.api;

import com.corabank.accounts_api.model.dto.CreateAccountRequest;
import com.corabank.accounts_api.model.Account;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.corabank.accounts_api.model.dto.AccountResponse;

import java.util.List;

@Tag(name = "Account", description = "Account API - Gerenciamento de contas bancárias")
@RequestMapping("/accounts")
public interface AccountApi {

    @PostMapping
    @Operation(
            summary = "Criar conta",
            description = "Cria uma nova conta bancária com nome e CPF",
            tags = {"Account"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Conta criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AccountResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Dados inválidos na requisição",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\": \"2026-02-07T11:30:00\", \"status\": 400, \"error\": \"Bad Request\", \"message\": \"CPF deve conter exatamente 11 dígitos\", \"path\": \"/accounts\"}")
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(example = "{\"timestamp\": \"2026-02-07T11:30:00\", \"status\": 500, \"error\": \"Internal Server Error\", \"message\": \"Erro ao processar requisição\", \"path\": \"/accounts\"}")
                    )
            )
    })
    ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request);


    @GetMapping
    @Operation(
            summary = "Listar contas",
            description = "Retorna lista de todas as contas cadastradas",
            tags = {"Account"}
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de contas retornada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(type = "array", implementation = AccountResponse.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor"
            )
    })
    ResponseEntity<List<AccountResponse>> listAccounts();

}


