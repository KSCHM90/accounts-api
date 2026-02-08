package com.corabank.accounts_api.service;

import com.corabank.accounts_api.model.Account;
import com.corabank.accounts_api.model.dto.AccountResponse;
import com.corabank.accounts_api.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public AccountResponse create(String name, String cpf) {
        Account account = Account.builder()
                .name(name)
                .cpf(cpf)
                .build();
        repository.save(account);
        return toResponse(account);

    }

    public List<AccountResponse> listAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private AccountResponse toResponse(Account account) {
        return AccountResponse.builder()
                .id(account.getId())
                .name(account.getName())
                .cpf(account.getCpf())
                .build();
    }
}

