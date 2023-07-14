package com.example.security.starter.keycloak.feign;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

@RequiredArgsConstructor
@Slf4j
public class OAuth2FeignRequestInterceptor implements RequestInterceptor {
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_TOKEN_TYPE = "Bearer";

	@Override
	public void apply(RequestTemplate template) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
			log.warn("Cannot find security context");
			return;
		}

		JwtAuthenticationToken oauthToken = (JwtAuthenticationToken) authentication;
		String token = oauthToken.getToken().getTokenValue();
		if (template.headers().containsKey(AUTHORIZATION_HEADER)) {
			log.warn("The Authorization token has been already set");
		} else if (token == null) {
			log.warn("Can not obtain existing token for request, if it is a non secured request, ignore.");
		} else {
			log.info("Constructing Header {} for Token {}", AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE);
			template.header(AUTHORIZATION_HEADER, String.format("%s %s", BEARER_TOKEN_TYPE, token));
		}
	}


}