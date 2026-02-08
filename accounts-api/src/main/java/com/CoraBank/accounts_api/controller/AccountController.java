package com.corabank.accounts_api.controller;

import com.corabank.accounts_api.controller.api.AccountApi;
import com.corabank.accounts_api.model.dto.AccountResponse;
import com.corabank.accounts_api.model.dto.CreateAccountRequest;
import com.corabank.accounts_api.model.Account;
import com.corabank.accounts_api.service.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController implements AccountApi {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @Override
    @PostMapping
    public ResponseEntity<AccountResponse> createAccount(@Valid @RequestBody CreateAccountRequest request) {
        AccountResponse response = service.create(request.name(), request.cpf());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @GetMapping
    public ResponseEntity<List<AccountResponse>> listAccounts() {
        List<AccountResponse> accounts = service.listAll();
        return ResponseEntity.ok(accounts);
    }
}



