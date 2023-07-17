package com.example.accountservice.web;

import com.example.accountservice.entities.Account;
import com.example.accountservice.exception.AccountNotFoundException;
import com.example.accountservice.services.AccountServices;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountControllerTest {

    @Mock
    private AccountServices accountServices;

    @InjectMocks
    private AccountController accountController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateAccount() {
        Account account = new Account();
        Account createdAccount = new Account();
        when(accountServices.create(account)).thenReturn(createdAccount);

        ResponseEntity<Account> responseEntity = accountController.createAccount(account);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(createdAccount, responseEntity.getBody());
        verify(accountServices, times(1)).create(account);
    }

    @Test
    void testUpdateAccount() {
        Integer accountNo = 1;
        Account account = new Account();
        account.setAccountNo(accountNo);
        Account updatedAccount = new Account();
        when(accountServices.update(account)).thenReturn(updatedAccount);

        ResponseEntity<Account> responseEntity = accountController.updateAccount(accountNo, account);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedAccount, responseEntity.getBody());
        verify(accountServices, times(1)).update(account);
    }

    @Test
    void testGetAccount() {
        Integer accountNo = 1;
        Account account = new Account();
        when(accountServices.getAccount(accountNo)).thenReturn(Optional.of(account));

        ResponseEntity<Account> responseEntity = accountController.getAccount(accountNo);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(account, responseEntity.getBody());
        verify(accountServices, times(1)).getAccount(accountNo);
    }

    @Test
    void testGetAccount_NotFound() {
        Integer accountNo = 1;
        when(accountServices.getAccount(accountNo)).thenReturn(Optional.empty());

        AccountNotFoundException exception = assertThrows(AccountNotFoundException.class,
                () -> accountController.getAccount(accountNo));

        assertEquals("Account not found", exception.getMessage());
        verify(accountServices, times(1)).getAccount(accountNo);
    }

    // Add more test methods as needed

}
