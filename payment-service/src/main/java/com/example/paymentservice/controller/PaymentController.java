package com.example.paymentservice.controller;

import com.example.paymentservice.controller.feign.ReportingClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/process")
@Slf4j
@RequiredArgsConstructor
public class PaymentController {

    private final ReportingClient reportingClient;


    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "/{name}")
    public ResponseEntity<String> getClient(@PathVariable("name") String name) {
        log.info("request to api to process name: {}", name);
        reportingClient.findAllData().forEach(log::info);
        return new ResponseEntity<>(String.format("hello %s", name ), HttpStatus.OK);
    }
}

