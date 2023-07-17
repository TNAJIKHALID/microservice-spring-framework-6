package com.sqli.accountservice.repositories;

import com.sqli.accountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNo(Integer accountNo);
}

