package com.example.accountservice.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/v1/account")
@Slf4j
public class AccountController {

    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping(value = "/{name}")
    public ResponseEntity<String> getClient(@PathVariable("name") String name) {
        log.info("request to api to process name: {}", name);
        return new ResponseEntity<>(String.format("hello %s", name ), HttpStatus.OK);
    }
}

