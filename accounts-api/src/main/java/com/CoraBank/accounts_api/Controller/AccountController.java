package com.CoraBank.accounts_api.Controller;

import com.CoraBank.accounts_api.DTO.CreateAccountRequest;
import com.CoraBank.accounts_api.Model.Account;
import com.CoraBank.accounts_api.Service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@CrossOrigin
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody CreateAccountRequest request) {
        return service.create(request.name(), request.cpf());
    }

    @GetMapping
    public List<Account> list() {
        return service.listAll();
    }
}

