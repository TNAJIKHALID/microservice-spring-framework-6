package com.sqli.transactionservice.services.impl;

import com.sqli.transactionservice.entities.TransactionBk;
import com.sqli.transactionservice.exception.TransactionNotFoundException;
import com.sqli.transactionservice.repositories.RequestsBkRepository;
import com.sqli.transactionservice.repositories.TransactionBkRepository;
import com.sqli.transactionservice.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionServiceImplTest {

    @Mock
    private TransactionBkRepository transactionBkRepository;

    @Mock
    private RequestsBkRepository requestsBkRepository;

    private TransactionService transactionService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        transactionService = new TransactionServiceImpl(transactionBkRepository, requestsBkRepository);
    }

    @Test
    public void testGetTransactionBk_ExistingTransId_ReturnsOptionalWithTransactionBk() {
        // Arrange
        int transId = 1;
        TransactionBk transactionBk = new TransactionBk();
        Mockito.when(transactionBkRepository.findByTransId(transId)).thenReturn(Optional.of(transactionBk));

        // Act
        Optional<TransactionBk> result = transactionService.getTransactionBk(transId);

        // Assert
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(transactionBk, result.get());
    }

    @Test
    public void testGetTransactionBk_NonExistingTransId_ThrowsTransactionNotFindException() {
        // Arrange
        int transId = 1;
        Mockito.when(transactionBkRepository.findByTransId(transId)).thenReturn(Optional.empty());

        // Act and Assert
        Assertions.assertThrows(TransactionNotFoundException.class, () -> {
            transactionService.getTransactionBk(transId);
        });
    }

    @Test
    public void testGetStatement_ReturnsListOfTransactionBks() {
        // Arrange
        int accountNo = 123;
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now();
        List<TransactionBk> transactionBks = new ArrayList<>();
        Mockito.when(transactionBkRepository.getTransactionBksByAccountNoAndTransDateIsBetween(accountNo, startDate, endDate))
                .thenReturn(transactionBks);

        // Act
        List<TransactionBk> result = transactionService.getStatement(accountNo, startDate, endDate);

        // Assert
        Assertions.assertEquals(transactionBks, result);
    }

}
