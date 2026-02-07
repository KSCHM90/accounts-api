package com.CoraBank.accounts_api.Service;

import com.CoraBank.accounts_api.Model.Account;
import com.CoraBank.accounts_api.Repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository repository;

    public AccountService(AccountRepository repository) {
        this.repository = repository;
    }

    public Account create(String name, String cpf) {
        Account account = Account.builder()
                .name(name)
                .cpf(cpf)
                .build();

        return repository.save(account);
    }

    public List<Account> listAll() {
        return repository.findAll();
    }
}

