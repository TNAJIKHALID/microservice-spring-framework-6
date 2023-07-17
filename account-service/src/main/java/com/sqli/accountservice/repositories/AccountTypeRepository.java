package com.sqli.accountservice.repositories;

import com.sqli.accountservice.entities.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountTypeRepository extends JpaRepository<AccountType, Integer> {
}
