package com.example.accountservice.services.impl;

import com.example.accountservice.entities.Account;
import com.example.accountservice.entities.Balance;
import com.example.accountservice.exception.AccountNotFindException;
import com.example.accountservice.repositories.AccountRepository;
import com.example.accountservice.repositories.BalanceRepository;
import com.example.accountservice.services.AccountServices;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServicesImpl implements AccountServices {
    AccountRepository accountRepository;
    BalanceRepository balanceRepository;
    @Override
    public Account create(Account account) {
        Account a = accountRepository.save(account);
        Balance balance =Balance.builder()
                .accountNo(a.getAccountNo())
                .accountType("")
                .balance(0.0)
                .build();
        balanceRepository.save(balance);
        return a;
    }
    @Override
    public Account update(Account account) {
        Account a = accountRepository.findByAccountNo(account.getAccountNo()).orElseThrow(()
                -> new AccountNotFindException("Account not find"));
        return accountRepository.save(account);
    }
    @Override
    public Optional<Account> getAccount(Integer accountNo) {
        return Optional.ofNullable(accountRepository.findByAccountNo(accountNo).orElseThrow(()
                -> new AccountNotFindException("Account not find")));
    }

}
