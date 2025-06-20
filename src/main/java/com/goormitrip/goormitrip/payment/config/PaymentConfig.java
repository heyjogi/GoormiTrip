package com.goormitrip.goormitrip.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PaymentConfig {
	@Bean
	public WebClient tossWebClient() {
		return WebClient.builder()
			.baseUrl("https://api.tosspayments.com")
			.build();
	}
}