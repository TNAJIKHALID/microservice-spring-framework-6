package com.example.accountservice.repositories;

import com.example.accountservice.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByAccountNo(Integer accountNo);
}

