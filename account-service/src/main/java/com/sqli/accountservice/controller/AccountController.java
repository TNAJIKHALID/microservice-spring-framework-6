package com.sqli.accountservice.controller;

import com.sqli.accountservice.entities.Account;
import com.sqli.accountservice.exception.AccountNotFoundException;
import com.sqli.accountservice.services.AccountServices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/accounts")
@AllArgsConstructor
public class AccountController {

    private final AccountServices accountServices;


    @PostMapping
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = accountServices.create(account);
        return new ResponseEntity<>(createdAccount, HttpStatus.CREATED);
    }

    @PutMapping("/{accountNo}")
    public ResponseEntity<Account> updateAccount(@PathVariable Integer accountNo, @RequestBody Account account) {
        account.setAccountNo(accountNo);
        Account updatedAccount = accountServices.update(account);
        return new ResponseEntity<>(updatedAccount, HttpStatus.OK);
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<Account> getAccount(@PathVariable Integer accountNo) {
        Account account = accountServices.getAccount(accountNo)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return new ResponseEntity<>(account, HttpStatus.OK);
    }

}
