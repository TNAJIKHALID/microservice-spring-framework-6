package com.example.accountservice.repositories;

import com.example.accountservice.entities.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Integer> {
    Optional<Balance> findByAccountNo(Integer accountNo);

}