package com.sqli.accountservice.services.impl;

import com.sqli.accountservice.entities.Balance;
import com.sqli.accountservice.repositories.BalanceRepository;
import com.sqli.accountservice.exception.AccountNotFoundException;
import com.sqli.accountservice.services.BalanceService;
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
