package com.example.transactionservice.repositories;

import com.example.transactionservice.entities.RequestsBk;
import com.example.transactionservice.entities.TransactionBk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestsBkRepository extends JpaRepository<RequestsBk, Integer> {
    Optional<RequestsBk> getRequestsBkByRequestId(Integer requestId);
}
