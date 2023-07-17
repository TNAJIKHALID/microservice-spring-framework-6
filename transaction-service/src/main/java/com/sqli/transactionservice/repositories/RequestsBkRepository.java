package com.sqli.transactionservice.repositories;

import com.sqli.transactionservice.entities.RequestsBk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RequestsBkRepository extends JpaRepository<RequestsBk, Integer> {
    Optional<RequestsBk> getRequestsBkByRequestId(Integer requestId);
}
