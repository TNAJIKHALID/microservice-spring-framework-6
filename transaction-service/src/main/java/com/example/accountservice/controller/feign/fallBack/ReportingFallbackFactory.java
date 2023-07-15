package com.example.accountservice.controller.feign.fallBack;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReportingFallbackFactory implements FallbackFactory<FallbackWithFactory> {

    @Override
    public FallbackWithFactory create(Throwable cause) {
        log.error("A fallBack in the referential client was thrown: {}", cause.getMessage());
        return new FallbackWithFactory();
    }

}
