package com.sqli.transactionservice.services;

import com.sqli.transactionservice.entities.TransactionBk;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Optional<TransactionBk> getTransactionBk(Integer transId);

    List<TransactionBk> getStatement(Integer accountNo, LocalDate startDate, LocalDate endDate);
}
