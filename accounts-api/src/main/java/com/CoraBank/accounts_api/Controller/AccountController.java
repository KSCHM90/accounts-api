package com.CoraBank.accounts_api.Controller;

import com.CoraBank.accounts_api.DTO.CreateAccountRequest;
import com.CoraBank.accounts_api.Model.Account;
import com.CoraBank.accounts_api.Service.AccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
@Tag(name = "Accounts", description = "Endpoints para gerenciamento de contas bancárias")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Criar uma nova conta", description = "Cria uma nova conta bancária com nome e CPF")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Conta criada com sucesso"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos")
    })
    public Account create(@RequestBody CreateAccountRequest request) {
        return service.create(request.name(), request.cpf());
    }

    @GetMapping
    @Operation(summary = "Listar contas", description = "Retorna todas as contas cadastradas")
    @ApiResponse(responseCode = "200", description = "Lista de contas retornada com sucesso")
    public List<Account> list() {
        return service.listAll();
    }
}



