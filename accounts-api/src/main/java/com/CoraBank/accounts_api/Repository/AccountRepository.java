package com.CoraBank.accounts_api.Repository;

import com.CoraBank.accounts_api.Model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByCpf(String cpf);
}
