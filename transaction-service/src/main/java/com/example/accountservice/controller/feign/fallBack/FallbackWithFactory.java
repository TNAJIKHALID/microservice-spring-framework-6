package com.example.accountservice.controller.feign.fallBack;

import com.example.accountservice.controller.feign.ReportingClient;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;
@Slf4j
public class FallbackWithFactory implements ReportingClient {

    @Override
    public List<String> findAllData() {
        log.warn("FallBack from Reporting");
        return Collections.emptyList();
        //throw new NoFallbackAvailableException("Boom!", new RuntimeException());
    }


}
