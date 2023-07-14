package com.example.reportingservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tarnsactions")
@Slf4j
public class ReportingController {
    @GetMapping(value = "/{name}")
    public ResponseEntity<String> getClient(@PathVariable("name") String name) {
        log.info("request to reporting service api to get a report name: {}", name);
        return new ResponseEntity<>(String.format("Report %s", name ), HttpStatus.OK);
    }
}

