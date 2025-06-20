package com.goormitrip.goormitrip.payment.service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.goormitrip.goormitrip.payment.domain.TossPayment;
import com.goormitrip.goormitrip.payment.domain.TossPaymentMethod;
import com.goormitrip.goormitrip.payment.domain.TossPaymentStatus;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentRequest;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentResponse;
import com.goormitrip.goormitrip.payment.repository.TossPaymentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PaymentService {

	private final TossPaymentRepository repository;

	public ConfirmPaymentResponse confirmPayment(ConfirmPaymentRequest req) {
		String auth = "Basic " + Base64.getEncoder().encodeToString(("test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6:").getBytes());

		Map<String, Object> body = Map.of(
			"paymentKey", req.getPaymentKey(),
			"orderId", req.getOrderId(),
			"amount", req.getAmount()
		);


		TossPayment saved = repository.save(
			TossPayment.builder()
				.paymentId(UUID.randomUUID().toString().getBytes())
				.tossOrderId(req.getOrderId())
				.tossPaymentKey(req.getPaymentKey())
				.totalAmount(req.getAmount())
				.tossPaymentMethod(TossPaymentMethod.CARD)
				.tossPaymentStatus(TossPaymentStatus.PAID)
				.requestedAt(LocalDateTime.now())
				.approvedAt(LocalDateTime.now())
				.build()
		);

		return new ConfirmPaymentResponse(true, "결제 성공", req.getPaymentKey(), "paid", saved.getApprovedAt());
	}
}
