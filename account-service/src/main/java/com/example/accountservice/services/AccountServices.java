package com.example.accountservice.services;

import com.example.accountservice.entities.Account;

import java.util.Optional;

public interface AccountServices {
    Account create(Account account);
    Account update(Account account);
    Optional<Account> getAccount(Integer accountNo);
}
