package com.sqli.security.starter.keycloak;

import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;


@Configuration
@Slf4j
@ComponentScan(basePackages = "com.sqli")
@EnableMethodSecurity
public class SecurityConfiguration {
	@Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
	private String issuer;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, Converter<Jwt, JwtAuthenticationToken> jwtAuthenticationConverter) throws Exception {
		http.csrf().disable();
		http.authorizeHttpRequests(registry -> registry
						.anyRequest().authenticated())
				.oauth2ResourceServer()
				.jwt()
				.jwtAuthenticationConverter(jwtAuthenticationConverter).and();
		return http.build();
	}

	@Autowired
	@Bean
	public Converter<Jwt, Collection<GrantedAuthority>> keycloakGrantedAuthoritiesConverter(@Value("${keycloak.resource}") String clientId) {
		return new KeycloakGrantedAuthoritiesConverter(clientId);
	}

	@Bean
	public Converter<Jwt, JwtAuthenticationToken> keycloakJwtAuthenticationConverter(@Value("${keycloak.resource}") String clientId,
																					 KeycloakGrantedAuthoritiesConverter keycloakGrantedAuthoritiesConverter
	) {
		return new KeycloakJwtAuthenticationConverter(keycloakGrantedAuthoritiesConverter);
	}

	@Bean
	public JwtDecoder jwtDecoder() {
		return JwtDecoders.fromIssuerLocation(issuer);
	}

	@Bean
	public GrantedAuthorityDefaults grantedAuthorityDefaults() {
		return new GrantedAuthorityDefaults(Strings.EMPTY);
	}

}
