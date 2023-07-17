package com.sqli.transactionservice.services.impl;

import com.sqli.transactionservice.entities.TransactionBk;
import com.sqli.transactionservice.exception.TransactionNotFoundException;
import com.sqli.transactionservice.repositories.RequestsBkRepository;
import com.sqli.transactionservice.repositories.TransactionBkRepository;
import com.sqli.transactionservice.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    TransactionBkRepository transactionBkRepository;
    RequestsBkRepository requestsBkRepository;

    @Override
    public Optional<TransactionBk> getTransactionBk(Integer transId) {
        return Optional.ofNullable(transactionBkRepository.findByTransId(transId).orElseThrow(()
                -> new TransactionNotFoundException("Transaction not find")));
    }

    @Override
    public List<TransactionBk> getStatement(Integer accountNo, LocalDate startDate, LocalDate endDate) {
        List<TransactionBk> transactionBks = transactionBkRepository
                .getTransactionBksByAccountNoAndTransDateIsBetween(accountNo,startDate,endDate);
        return transactionBks;
    }

}
