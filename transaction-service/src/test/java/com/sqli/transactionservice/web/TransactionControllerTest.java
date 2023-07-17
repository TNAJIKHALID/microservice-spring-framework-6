package com.sqli.transactionservice.web;

import com.sqli.transactionservice.controller.TransactionController;
import com.sqli.transactionservice.entities.TransactionBk;
import com.sqli.transactionservice.exception.TransactionNotFoundException;
import com.sqli.transactionservice.services.TransactionService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class TransactionControllerTest {

    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        transactionController = new TransactionController(transactionService);
    }

    @Test
    public void testGetTransaction_ValidTransactionId_ReturnsTransaction() {
        Integer transId = 1;
        TransactionBk transaction = new TransactionBk();
        when(transactionService.getTransactionBk(transId)).thenReturn(Optional.of(transaction));

        ResponseEntity<TransactionBk> response = transactionController.getTransaction(transId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transaction, response.getBody());
        verify(transactionService, times(1)).getTransactionBk(transId);
    }

    @Test
    public void testGetTransaction_InvalidTransactionId_ThrowsTransactionNotFoundException() {
        Integer transId = 1;
        when(transactionService.getTransactionBk(transId)).thenReturn(Optional.empty());

        assertThrows(TransactionNotFoundException.class, () -> {
            transactionController.getTransaction(transId);
        });

        verify(transactionService, times(1)).getTransactionBk(transId);
    }

    @Test
    public void testGetStatement_ValidParameters_ReturnsStatement() {
        Integer accountNo = 123;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        List<TransactionBk> statement = Arrays.asList(new TransactionBk(), new TransactionBk());
        when(transactionService.getStatement(accountNo, startDate, endDate)).thenReturn(statement);

        ResponseEntity<List<TransactionBk>> response = transactionController.getStatement(accountNo, startDate, endDate);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(statement, response.getBody());
        verify(transactionService, times(1)).getStatement(accountNo, startDate, endDate);
    }
}
