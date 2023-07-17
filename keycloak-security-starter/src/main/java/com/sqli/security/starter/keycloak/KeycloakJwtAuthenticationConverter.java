package com.sqli.security.starter.keycloak;

import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public final class KeycloakJwtAuthenticationConverter implements Converter<Jwt, JwtAuthenticationToken> {

	private static final String USERNAME_CLAIM = "preferred_username";
	private final KeycloakGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter;


	@Override
	public JwtAuthenticationToken convert(Jwt jwt) {
		final var authorities = jwtGrantedAuthoritiesConverter.convert(jwt);
		final String username = extractUsername(jwt);
		return new JwtAuthenticationToken(jwt, authorities, username);
	}

	private String extractUsername(Jwt jwt) {
		return jwt.getClaims().containsKey(USERNAME_CLAIM) ? jwt.getClaimAsString(USERNAME_CLAIM) : jwt.getSubject();
	}

}