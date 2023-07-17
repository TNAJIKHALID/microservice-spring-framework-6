package com.example.accountservice.web;

import com.example.accountservice.entities.Balance;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.services.BalanceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BalanceControllerTest {

    @Mock
    private BalanceService balanceService;

    @InjectMocks
    private BalanceController balanceController;

    private Balance testBalance;

    @BeforeEach
    void setUp() {
        // Initialize test data
        testBalance = new Balance();
        testBalance.setAccountNo(123456789);
        testBalance.setBalance(1000.0);
    }

    @Test
    void testUpdateBalance() {
        // Prepare mock behavior
        when(balanceService.updateBalance(testBalance)).thenReturn(testBalance);

        // Perform the API call
        ResponseEntity<Balance> response = balanceController.updateBalance(testBalance.getAccountNo(), testBalance);

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testBalance, response.getBody());

        // Verify the service method was called
        verify(balanceService, times(1)).updateBalance(testBalance);
    }

    @Test
    void testGetBalance() {
        // Prepare mock behavior
        when(balanceService.getBalance(testBalance.getAccountNo())).thenReturn(Optional.of(testBalance));

        // Perform the API call
        ResponseEntity<Balance> response = balanceController.getBalance(testBalance.getAccountNo());

        // Verify the response
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(testBalance, response.getBody());

        // Verify the service method was called
        verify(balanceService, times(1)).getBalance(testBalance.getAccountNo());
    }

    @Test
    void testGetBalance_NotFound() {
        // Prepare mock behavior
        when(balanceService.getBalance(testBalance.getAccountNo())).thenReturn(Optional.empty());

        // Perform the API call and verify the exception
        assertThrows(AccountNotFoundException.class, () -> balanceController.getBalance(testBalance.getAccountNo()));

        // Verify the service method was called
        verify(balanceService, times(1)).getBalance(testBalance.getAccountNo());
    }
}
