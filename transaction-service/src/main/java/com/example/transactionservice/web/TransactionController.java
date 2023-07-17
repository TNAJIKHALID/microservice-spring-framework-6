package com.example.transactionservice.web;

import com.example.transactionservice.entities.TransactionBk;
import com.example.transactionservice.exception.TransactionNotFoundException;
import com.example.transactionservice.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@AllArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/{transId}")
    public ResponseEntity<TransactionBk> getTransaction(@PathVariable Integer transId) {
        TransactionBk transaction = transactionService.getTransactionBk(transId)
                .orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));

        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @GetMapping("/statement")
    public ResponseEntity<List<TransactionBk>> getStatement(@RequestParam Integer accountNo,
                                                            @RequestParam Date startDate,
                                                            @RequestParam Date endDate) {
        List<TransactionBk> statement = transactionService.getStatement(accountNo, startDate, endDate);

        return new ResponseEntity<>(statement, HttpStatus.OK);
    }
}
