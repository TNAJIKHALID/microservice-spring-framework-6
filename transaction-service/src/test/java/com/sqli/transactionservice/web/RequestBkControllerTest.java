package com.sqli.transactionservice.web;

import com.sqli.transactionservice.controller.RequestBkController;
import com.sqli.transactionservice.entities.RequestsBk;
import com.sqli.transactionservice.entities.TransactionBk;
import com.sqli.transactionservice.services.RequestBkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RequestBkControllerTest {

    private RequestBkController requestBkController;

    @Mock
    private RequestBkService requestBkService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        requestBkController = new RequestBkController(requestBkService);
    }

    @Test
    public void testCreateRequest() {
        RequestsBk requestsBk = new RequestsBk();
        when(requestBkService.createRequest(any(RequestsBk.class))).thenReturn(requestsBk);

        ResponseEntity<RequestsBk> response = requestBkController.createRequest(requestsBk);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(requestsBk, response.getBody());
        verify(requestBkService, times(1)).createRequest(any(RequestsBk.class));
    }

    @Test
    public void testConfirmRequest() {
        Integer requestId = 1;
        TransactionBk transactionBk = new TransactionBk();
        when(requestBkService.confirmRequest(requestId)).thenReturn(transactionBk);

        ResponseEntity<TransactionBk> response = requestBkController.confirmRequest(requestId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(transactionBk, response.getBody());
        verify(requestBkService, times(1)).confirmRequest(requestId);
    }
}
