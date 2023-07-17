package com.sqli.accountservice.services;

import com.sqli.accountservice.entities.Balance;

import java.util.Optional;

public interface BalanceService {
    Balance updateBalance(Balance balance);
    Optional<Balance> getBalance(Integer accountNo);

}
