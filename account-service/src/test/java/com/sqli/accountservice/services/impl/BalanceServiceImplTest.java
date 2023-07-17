package com.sqli.accountservice.services.impl;

import com.sqli.accountservice.entities.Balance;
import com.sqli.accountservice.exception.AccountNotFoundException;
import com.sqli.accountservice.repositories.BalanceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class BalanceServiceImplTest {

    @Mock
    private BalanceRepository balanceRepository;

    @InjectMocks
    private BalanceServiceImpl balanceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateBalance() {
        Integer accountNo = 1;
        Balance balance = new Balance();
        balance.setAccountNo(accountNo);
        when(balanceRepository.findByAccountNo(accountNo)).thenReturn(Optional.of(balance));
        when(balanceRepository.save(balance)).thenReturn(balance);

        Balance updatedBalance = balanceService.updateBalance(balance);

        assertEquals(balance, updatedBalance);
        verify(balanceRepository, times(1)).findByAccountNo(accountNo);
        verify(balanceRepository, times(1)).save(balance);
    }

    @Test
    void testUpdateBalance_NotFound() {
        Integer accountNo = 1;
        Balance balance = new Balance();
        balance.setAccountNo(accountNo);
        when(balanceRepository.findByAccountNo(accountNo)).thenReturn(Optional.empty());

        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> balanceService.updateBalance(balance));

        assertEquals("balance not find", exception.getMessage());
        verify(balanceRepository, times(1)).findByAccountNo(accountNo);
        verify(balanceRepository, never()).save(balance);
    }

    @Test
    void testGetBalance() {
        Integer accountNo = 1;
        Balance balance = new Balance();
        when(balanceRepository.findByAccountNo(accountNo)).thenReturn(Optional.of(balance));

        Optional<Balance> result = balanceService.getBalance(accountNo);

        assertEquals(Optional.of(balance), result);
        verify(balanceRepository, times(1)).findByAccountNo(accountNo);
    }

    @Test
    void testGetBalance_NotFound() {
        Integer accountNo = 1;
        when(balanceRepository.findByAccountNo(accountNo)).thenReturn(Optional.empty());

        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> balanceService.getBalance(accountNo));

        assertEquals("Account not find", exception.getMessage());
        verify(balanceRepository, times(1)).findByAccountNo(accountNo);
    }

    // Add more test methods as needed

}
