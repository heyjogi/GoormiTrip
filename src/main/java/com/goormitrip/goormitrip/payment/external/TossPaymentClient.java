package com.goormitrip.goormitrip.payment.external;

import java.util.*;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import com.goormitrip.goormitrip.payment.exception.PaymentCancelFailedException;
import com.goormitrip.goormitrip.payment.exception.PaymentFailedException;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TossPaymentClient {

	private final WebClient tossWebClient;
	private final String secretKey = "test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6";

	private String authHeader() {
		return "Basic " + Base64.getEncoder().encodeToString((secretKey + ":").getBytes());
	}

	public Map<String, Object> confirmPayment(String paymentKey, String orderId, long amount) {
		try {
			return tossWebClient.post()
				.uri("/v1/payments/confirm")
				.header("Authorization", authHeader())
				.header("Content-Type", "application/json")
				.bodyValue(Map.of("paymentKey", paymentKey, "orderId", orderId, "amount", amount))
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
				.block();
		} catch (Exception e) {
			throw new PaymentFailedException();
		}
	}

	public void cancelPayment(String paymentKey, long amount) {
		try {
			tossWebClient.post()
				.uri("/v1/payments/" + paymentKey + "/cancel")
				.header("Authorization", authHeader())
				.header("Content-Type", "application/json")
				.bodyValue(Map.of("cancelReason", "고객 요청 취소", "cancelAmount", amount))
				.retrieve()
				.bodyToMono(Void.class)
				.block();
		} catch (Exception e) {
			throw new PaymentCancelFailedException();
		}
	}
}
