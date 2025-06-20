package com.goormitrip.goormitrip.payment.service.impl;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import com.goormitrip.goormitrip.payment.domain.TossPayment;
import com.goormitrip.goormitrip.payment.domain.TossPaymentMethod;
import com.goormitrip.goormitrip.payment.domain.TossPaymentStatus;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentRequest;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentResponse;
import com.goormitrip.goormitrip.payment.exception.InvalidAmountException;
import com.goormitrip.goormitrip.payment.exception.PaymentFailedException;
import com.goormitrip.goormitrip.payment.exception.UnsupportedMethodException;
import com.goormitrip.goormitrip.payment.repository.TossPaymentRepository;
import com.goormitrip.goormitrip.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

	private final TossPaymentRepository repository;
	private final WebClient tossWebClient;

	@Override
	@Transactional
	public ConfirmPaymentResponse confirmPayment(ConfirmPaymentRequest req) {
		String auth = "Basic " + Base64.getEncoder()
			.encodeToString(("test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6:").getBytes());

		Map<String, Object> body = Map.of(
			"paymentKey", req.getPaymentKey(),
			"orderId", req.getOrderId(),
			"amount", req.getAmount()
		);

		Map<String, Object> tossRes;
		try {
			tossRes = tossWebClient.post()
				.uri("/v1/payments/confirm")
				.header("Authorization", auth)
				.header("Content-Type", "application/json")
				.bodyValue(body)
				.retrieve()
				.bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
				.block();
		} catch (Exception e) {
			throw new PaymentFailedException();
		}

		if (tossRes == null || !"DONE".equalsIgnoreCase(String.valueOf(tossRes.get("status")))) {
			throw new PaymentFailedException();
		}

		long paidAmount = ((Number) tossRes.get("amount")).longValue();
		if (paidAmount != req.getAmount()) {
			throw new InvalidAmountException();
		}

		TossPaymentMethod method;
		try {
			method = TossPaymentMethod.valueOf(((String) tossRes.get("method")).toUpperCase());
		} catch (Exception e) {
			throw new UnsupportedMethodException();
		}

		TossPayment saved = repository.save(
			TossPayment.builder()
				.paymentId(UUID.randomUUID().toString().getBytes())
				.tossOrderId(req.getOrderId())
				.tossPaymentKey(req.getPaymentKey())
				.totalAmount(paidAmount)
				.tossPaymentMethod(method)
				.tossPaymentStatus(TossPaymentStatus.PAID)
				.requestedAt(LocalDateTime.now())
				.approvedAt(LocalDateTime.now())
				.build()
		);

		return ConfirmPaymentResponse.builder()
			.paymentId(req.getPaymentKey())
			.status("paid")
			.approvedAt(saved.getApprovedAt())
			.build();
	}
}
