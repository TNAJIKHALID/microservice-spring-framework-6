package com.example.transactionservice.services;

import com.example.transactionservice.controller.feign.fallBack.ReportingFallbackFactory;
import com.example.transactionservice.dto.request.BalanceRequest;
import com.example.transactionservice.dto.respanse.BalanceResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "${api.balance.name}" , url = "${api.balance.url}", fallbackFactory = ReportingFallbackFactory.class)
public interface BalanceClient {
    @PutMapping("/api/v1/accounts/{accountNo}")
    public ResponseEntity<BalanceResponse> updateBalance(@PathVariable Integer accountNo, @RequestBody BalanceRequest balance) ;
}
