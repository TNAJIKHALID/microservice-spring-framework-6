package com.example.accountservice.services.impl;

import com.example.accountservice.entities.Account;
import com.example.accountservice.entities.Balance;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.repositories.AccountRepository;
import com.example.accountservice.repositories.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccountServicesImplTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private AccountServicesImpl accountServices;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Account account = new Account();
        when(accountRepository.save(account)).thenReturn(account);

        Account result = accountServices.create(account);

        assertEquals(account, result);
        verify(accountRepository, times(1)).save(account);

        // Additional assertions for balance creation
        verify(balanceRepository, times(1)).save(any(Balance.class));
    }

    @Test
    void testUpdate() {
        Integer accountNo = 1;
        Account existingAccount = new Account();
        existingAccount.setAccountNo(accountNo);

        Account updatedAccount = new Account();
        updatedAccount.setAccountNo(accountNo);

        when(accountRepository.findByAccountNo(accountNo)).thenReturn(Optional.of(existingAccount));
        when(accountRepository.save(updatedAccount)).thenReturn(updatedAccount);

        Account result = accountServices.update(updatedAccount);

        assertEquals(updatedAccount, result);
        verify(accountRepository, times(1)).findByAccountNo(accountNo);
        verify(accountRepository, times(1)).save(updatedAccount);
    }

    @Test
    void testUpdate_NotFound() {
        Integer accountNo = 1;
        Account updatedAccount = new Account();
        updatedAccount.setAccountNo(accountNo);

        when(accountRepository.findByAccountNo(accountNo)).thenReturn(Optional.empty());

        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> accountServices.update(updatedAccount));

        assertEquals("Account not find", exception.getMessage());
        verify(accountRepository, times(1)).findByAccountNo(accountNo);
        verify(accountRepository, never()).save(updatedAccount);
    }

    @Test
    void testGetAccount() {
        Integer accountNo = 1;
        Account account = new Account();
        account.setAccountNo(accountNo);

        when(accountRepository.findByAccountNo(accountNo)).thenReturn(Optional.of(account));

        Optional<Account> result = accountServices.getAccount(accountNo);

        assertTrue(result.isPresent());
        assertEquals(account, result.get());
        verify(accountRepository, times(1)).findByAccountNo(accountNo);
    }

    @Test
    void testGetAccount_NotFound() {
        Integer accountNo = 1;

        when(accountRepository.findByAccountNo(accountNo)).thenReturn(Optional.empty());

        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> accountServices.getAccount(accountNo));

        assertEquals("Account not find", exception.getMessage());
        verify(accountRepository, times(1)).findByAccountNo(accountNo);
    }

    // Add more test methods as needed

}
