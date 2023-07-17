package com.sqli.transactionservice.services.impl;

import com.sqli.transactionservice.dto.request.BalanceRequest;
import com.sqli.transactionservice.dto.respanse.BalanceResponse;
import com.sqli.transactionservice.entities.RequestsBk;
import com.sqli.transactionservice.entities.TransactionBk;
import com.sqli.transactionservice.exception.RequestNotFoundException;
import com.sqli.transactionservice.repositories.RequestsBkRepository;
import com.sqli.transactionservice.repositories.TransactionBkRepository;
import com.sqli.transactionservice.services.BalanceClient;
import com.sqli.transactionservice.services.RequestBkService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Optional;

public class RequestBkServiceImplTest {

    @Mock
    private RequestsBkRepository requestsBkRepository;

    @Mock
    private TransactionBkRepository transactionBkRepository;

    @Mock
    private BalanceClient balanceClient;

    private RequestBkService requestBkService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        requestBkService = new RequestBkServiceImpl(requestsBkRepository, transactionBkRepository, balanceClient);
    }

    @Test
    public void testCreateRequest() {
        RequestsBk requestsBk = RequestsBk.builder()
                .accountNo(12345)
                .amount(100.0)
                .toAccount(56789)
                .message("Test request")
                .build();

        Mockito.when(requestsBkRepository.save(Mockito.any())).thenReturn(requestsBk);

        RequestsBk createdRequest = requestBkService.createRequest(requestsBk);

        Assertions.assertNotNull(createdRequest);
        Assertions.assertEquals(requestsBk.getAccountNo(), createdRequest.getAccountNo());
        Assertions.assertEquals(requestsBk.getAmount(), createdRequest.getAmount());
        Assertions.assertEquals(requestsBk.getToAccount(), createdRequest.getToAccount());
        Assertions.assertEquals(requestsBk.getMessage(), createdRequest.getMessage());
        Assertions.assertNotNull(createdRequest.getRequestDate());
        Assertions.assertFalse(createdRequest.getHasViewed());
        Assertions.assertEquals("in progress", createdRequest.getStatus());

        Mockito.verify(requestsBkRepository, Mockito.times(1)).save(Mockito.any());
    }

    @Test
    public void testConfirmRequest() {
        Integer requestId = 1;
        RequestsBk requestsBk = RequestsBk.builder()
                .requestId(requestId)
                .accountNo(12345)
                .amount(100.0)
                .toAccount(56789)
                .message("Test request")
                .build();

        TransactionBk transactionBk = TransactionBk.builder()
                .toAccount(requestsBk.getToAccount())
                .transDate(LocalDate.now())
                .accountNo(requestsBk.getAccountNo())
                .amount(requestsBk.getAmount())
                .purpose(requestsBk.getMessage())
                .build();

        BalanceRequest balanceRequest = BalanceRequest.builder()
                .accountNo(requestsBk.getToAccount())
                .balance(requestsBk.getAmount())
                .build();

        BalanceResponse balanceResponse = BalanceResponse.builder()
                .accountNo(requestsBk.getToAccount())
                .balance(1000.0)
                .build();

        Mockito.when(requestsBkRepository.getRequestsBkByRequestId(requestId)).thenReturn(Optional.of(requestsBk));
        Mockito.when(transactionBkRepository.save(Mockito.any())).thenReturn(transactionBk);
        Mockito.when(balanceClient.updateBalance(requestsBk.getToAccount(), balanceRequest))
                .thenReturn(ResponseEntity.ok(balanceResponse));

        TransactionBk confirmedTransaction = requestBkService.confirmRequest(requestId);

        Assertions.assertNotNull(confirmedTransaction);
        Assertions.assertEquals(transactionBk.getToAccount(), confirmedTransaction.getToAccount());
        Assertions.assertEquals(transactionBk.getAccountNo(), confirmedTransaction.getAccountNo());
        Assertions.assertEquals(transactionBk.getAmount(), confirmedTransaction.getAmount());
        Assertions.assertEquals(transactionBk.getPurpose(), confirmedTransaction.getPurpose());

        Assertions.assertEquals("valid", requestsBk.getStatus());
        Assertions.assertTrue(requestsBk.getHasViewed());

        Mockito.verify(requestsBkRepository, Mockito.times(1)).getRequestsBkByRequestId(requestId);
        Mockito.verify(transactionBkRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(balanceClient, Mockito.times(1)).updateBalance(requestsBk.getToAccount(), balanceRequest);
    }

    @Test
    public void testConfirmRequestRequestNotFindException() {
        Integer requestId = 1;

        Mockito.when(requestsBkRepository.getRequestsBkByRequestId(requestId)).thenReturn(Optional.empty());

        Assertions.assertThrows(RequestNotFoundException.class, () -> {
            requestBkService.confirmRequest(requestId);
        });

        Mockito.verify(requestsBkRepository, Mockito.times(1)).getRequestsBkByRequestId(requestId);
        Mockito.verify(transactionBkRepository, Mockito.never()).save(Mockito.any());
        Mockito.verify(balanceClient, Mockito.never()).updateBalance(Mockito.any(), Mockito.any());
    }
}
