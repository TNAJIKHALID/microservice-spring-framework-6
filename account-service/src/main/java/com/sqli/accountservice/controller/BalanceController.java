package com.sqli.accountservice.controller;

import com.sqli.accountservice.entities.Balance;
import com.sqli.accountservice.exception.AccountNotFoundException;
import com.sqli.accountservice.services.BalanceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/balances")
@AllArgsConstructor
public class BalanceController {

    private final BalanceService balanceService;

    @PutMapping("/{accountNo}")
    public ResponseEntity<Balance> updateBalance(@PathVariable Integer accountNo, @RequestBody Balance balance) {
        balance.setAccountNo(accountNo);
        Balance updatedBalance = balanceService.updateBalance(balance);
        return new ResponseEntity<>(updatedBalance, HttpStatus.OK);
    }

    @GetMapping("/{accountNo}")
    public ResponseEntity<Balance> getBalance(@PathVariable Integer accountNo) {
        Balance balance = balanceService.getBalance(accountNo)
                .orElseThrow(() -> new AccountNotFoundException("Balance not found"));
        return new ResponseEntity<>(balance, HttpStatus.OK);
    }
}
