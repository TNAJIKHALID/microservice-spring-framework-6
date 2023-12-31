package com.sqli.transactionservice.controller;

import com.sqli.transactionservice.entities.RequestsBk;
import com.sqli.transactionservice.entities.TransactionBk;
import com.sqli.transactionservice.services.RequestBkService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/requests")
@AllArgsConstructor
public class RequestBkController {

    private final RequestBkService requestBkService;

    @PostMapping
    public ResponseEntity<RequestsBk> createRequest(@RequestBody RequestsBk requestsBk) {
        RequestsBk createdRequest = requestBkService.createRequest(requestsBk);
        return new ResponseEntity<>(createdRequest, HttpStatus.CREATED);
    }

    @PutMapping("/{requestId}/confirm")
    public ResponseEntity<TransactionBk> confirmRequest(@PathVariable Integer requestId) {
        TransactionBk confirmedTransaction = requestBkService.confirmRequest(requestId);
        return new ResponseEntity<>(confirmedTransaction, HttpStatus.OK);
    }
}
