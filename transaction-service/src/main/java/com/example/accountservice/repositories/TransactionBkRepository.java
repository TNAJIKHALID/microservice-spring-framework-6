package com.example.accountservice.repositories;

import com.example.accountservice.entities.TransactionBk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface TransactionBkRepository extends JpaRepository<TransactionBk, Integer> {
    List<TransactionBk> getTransactionBksByTrAndTransDateIsBetween(Date start, Date end);
}
