package com.sqli.transactionservice.services;

import com.sqli.transactionservice.entities.RequestsBk;
import com.sqli.transactionservice.entities.TransactionBk;

public interface RequestBkService {
    RequestsBk createRequest(RequestsBk requestsBk);
    TransactionBk confirmRequest(Integer requestId);
}
