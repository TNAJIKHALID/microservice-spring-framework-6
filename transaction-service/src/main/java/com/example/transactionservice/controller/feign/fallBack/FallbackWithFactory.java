package com.example.transactionservice.controller.feign.fallBack;

import com.example.transactionservice.controller.feign.ReportingClient;
import com.example.transactionservice.dto.request.BalanceRequest;
import com.example.transactionservice.dto.respanse.BalanceResponse;
import com.example.transactionservice.services.BalanceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Slf4j
public class FallbackWithFactory implements BalanceClient {


    @Override
    public ResponseEntity<BalanceResponse> updateBalance(Integer accountNo, BalanceRequest balance) {
        return new ResponseEntity<BalanceResponse>(HttpStatus.NOT_FOUND);
    }
}
