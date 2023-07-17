package com.example.transactionservice.repositories;

import com.example.transactionservice.entities.TransactionBk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionBkRepository extends JpaRepository<TransactionBk, Integer> {
    @Query("SELECT T FROM TransactionBk T WHERE T.accountNo = :accountNo AND T.transDate BETWEEN :start AND :end")
    List<TransactionBk> getTransactionBksByAccountNoAndTransDateIsBetween(
            @Param("accountNo") Integer accountNo,
            @Param("start") Date start,
            @Param("end") Date end
    );

    Optional<TransactionBk> findByTransId(Integer transId);
}
