package com.example.transactionservice.services.impl;

import com.example.transactionservice.dto.request.BalanceRequest;
import com.example.transactionservice.dto.respanse.BalanceResponse;
import com.example.transactionservice.entities.RequestsBk;
import com.example.transactionservice.entities.TransactionBk;
import com.example.transactionservice.exception.RequestNotFoundException;
import com.example.transactionservice.repositories.RequestsBkRepository;
import com.example.transactionservice.repositories.TransactionBkRepository;
import com.example.transactionservice.services.BalanceClient;
import com.example.transactionservice.services.RequestBkService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@AllArgsConstructor
public class RequestBkServiceImpl implements RequestBkService {
    private final RequestsBkRepository requestsBkRepository;
    private final TransactionBkRepository transactionBkRepository;
    private final BalanceClient balanceClient;

    @Transactional
    @Override
    public RequestsBk createRequest(RequestsBk requestsBk) {
        requestsBk.setRequestDate(LocalDate.now());
        requestsBk.setHasViewed(false);
        requestsBk.setStatus("in progress");
        return requestsBkRepository.save(requestsBk);
    }

    @Transactional
    @Override
    public TransactionBk confirmRequest(Integer requestId) {
        RequestsBk requestsBk = requestsBkRepository.getRequestsBkByRequestId(requestId)
                .orElseThrow(() -> new RequestNotFoundException("Request ID not found"));

        TransactionBk transactionBk = TransactionBk.builder()
                .toAccount(requestsBk.getToAccount())
                .transDate(LocalDate.now())
                .accountNo(requestsBk.getAccountNo())
                .amount(requestsBk.getAmount())
                .purpose(requestsBk.getMessage())
                .build();
        transactionBkRepository.save(transactionBk);

        requestsBk.setStatus("valid");
        requestsBk.setHasViewed(true);
        requestsBkRepository.save(requestsBk);

        // Call account service to change balance
        BalanceRequest balanceRequest = BalanceRequest.builder()
                .accountNo(requestsBk.getToAccount())
                .balance(requestsBk.getAmount())
                .build();
        BalanceResponse b= balanceClient.updateBalance(requestsBk.getToAccount(), balanceRequest).getBody();

        return transactionBk;
    }
}
