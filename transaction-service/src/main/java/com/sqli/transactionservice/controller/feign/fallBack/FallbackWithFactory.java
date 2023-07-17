package com.sqli.transactionservice.controller.feign.fallBack;

import com.sqli.transactionservice.dto.request.BalanceRequest;
import com.sqli.transactionservice.dto.respanse.BalanceResponse;
import com.sqli.transactionservice.services.BalanceClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Slf4j
public class FallbackWithFactory implements BalanceClient {


    @Override
    public ResponseEntity<BalanceResponse> updateBalance(Integer accountNo, BalanceRequest balance) {
        return new ResponseEntity<BalanceResponse>(HttpStatus.NOT_FOUND);
    }
}
