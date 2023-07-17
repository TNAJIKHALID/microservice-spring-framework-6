package com.example.transactionservice.services;

import com.example.transactionservice.entities.RequestsBk;
import com.example.transactionservice.entities.TransactionBk;

public interface RequestBkService {
    RequestsBk createRequest(RequestsBk requestsBk);
    TransactionBk confirmRequest(Integer requestId);
}
