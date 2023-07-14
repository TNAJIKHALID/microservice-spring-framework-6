package com.example.paymentservice.controller.feign;


import com.example.paymentservice.controller.feign.fallBack.ReportingFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * We use only the name because we are using service registry & we set the property eureka.client.fetch-registry to true
 * Spring will fitch a copy from the instances registered in eureka this way we can access a service using its name
 * (the name it used to register to eureka discovery client)
 */
@FeignClient(name = "${api.reporting.name}" , url = "http://localhost:8283/", fallbackFactory = ReportingFallbackFactory.class)
public interface ReportingClient {

    @GetMapping("/reporting/api/transactions")
    List<String> findAllData();
}

