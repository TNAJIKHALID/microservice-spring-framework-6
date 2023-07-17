package com.sqli.accountservice.services.impl;

import com.sqli.accountservice.entities.Account;
import com.sqli.accountservice.entities.Balance;
import com.sqli.accountservice.repositories.AccountRepository;
import com.sqli.accountservice.repositories.BalanceRepository;
import com.sqli.accountservice.exception.AccountNotFoundException;
import com.sqli.accountservice.services.AccountServices;
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
                -> new AccountNotFoundException("Account not find"));
        return accountRepository.save(account);
    }
    @Override
    public Optional<Account> getAccount(Integer accountNo) {
        return Optional.ofNullable(accountRepository.findByAccountNo(accountNo).orElseThrow(()
                -> new AccountNotFoundException("Account not find")));
    }

}
