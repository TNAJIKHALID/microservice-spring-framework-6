package com.example.transactionservice.services.impl;

import com.example.transactionservice.entities.TransactionBk;
import com.example.transactionservice.exception.TransactionNotFoundException;
import com.example.transactionservice.repositories.RequestsBkRepository;
import com.example.transactionservice.repositories.TransactionBkRepository;
import com.example.transactionservice.services.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
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
    public List<TransactionBk> getStatement(Integer accountNo, Date startDate, Date endDate) {
        List<TransactionBk> transactionBks = transactionBkRepository
                .getTransactionBksByAccountNoAndTransDateIsBetween(accountNo,startDate,endDate);
        return transactionBks;
    }

}
