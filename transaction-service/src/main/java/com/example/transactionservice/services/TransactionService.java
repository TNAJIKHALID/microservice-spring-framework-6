package com.example.transactionservice.services;

import com.example.transactionservice.entities.RequestsBk;
import com.example.transactionservice.entities.TransactionBk;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Optional<TransactionBk> getTransactionBk(Integer transId);
    List<TransactionBk> getStatement( Integer accountNo, Date startDate, Date endDate);
}
