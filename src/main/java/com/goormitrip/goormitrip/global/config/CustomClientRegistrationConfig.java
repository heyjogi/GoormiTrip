package com.goormitrip.goormitrip.global.config;

import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;

@Configuration
public class CustomClientRegistrationConfig {

	@Bean
	public ClientRegistrationRepository clientRegistrationRepository(
			OAuth2ClientProperties props) {

		final ClientRegistration kakao = ClientRegistration.withRegistrationId("kakao")
				.clientId(props.getRegistration().get("kakao").getClientId())
				.clientSecret(props.getRegistration().get("kakao").getClientSecret())
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
				.scope("profile_nickname", "account_email", "profile_image")
				.authorizationUri("https://kauth.kakao.com/oauth/authorize")
				.tokenUri("https://kauth.kakao.com/oauth/token")
				.userInfoUri("https://kapi.kakao.com/v2/user/me")
				.userNameAttributeName("id")
				.clientName("Kakao")
				.build();

		return new InMemoryClientRegistrationRepository(
				kakao);
	}
}