package com.example.accountservice.services.impl;

import com.example.accountservice.entities.Balance;
import com.example.accountservice.repositories.BalanceRepository;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.services.BalanceService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BalanceServiceImpl implements BalanceService {
    BalanceRepository balanceRepository;

    @Override
    public Balance updateBalance(Balance balance) {
        Balance b = balanceRepository.findByAccountNo(balance.getAccountNo())
                .orElseThrow(() -> new AccountNotFoundException("balance not find"));
        return balanceRepository.save(balance);
    }

    @Override
    public Optional<Balance> getBalance(Integer accountNo) {
        return Optional.ofNullable(balanceRepository.findByAccountNo(accountNo).orElseThrow(()
                -> new AccountNotFoundException("Account not find")));
    }
}
