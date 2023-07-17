package com.example.accountservice.services;

import com.example.accountservice.entities.Balance;

import java.util.Optional;

public interface BalanceService {
    Balance updateBalance( Balance balance);
    Optional<Balance> getBalance(Integer accountNo);

}
