package com.sqli.security.starter.keycloak.feign;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass({ FeignClient.class })
public class OAuth2FeignAutoConfiguration {


	@Autowired
	public OAuth2FeignAutoConfiguration() {
	}

	@Bean
	public RequestInterceptor oauth2FeignRequestInterceptor() {
		return new OAuth2FeignRequestInterceptor();
	}

}