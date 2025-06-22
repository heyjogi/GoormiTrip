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
import com.goormitrip.goormitrip.payment.dto.PaymentCancelRequest;
import com.goormitrip.goormitrip.payment.dto.PaymentCancelResponse;
import com.goormitrip.goormitrip.payment.exception.InvalidAmountException;
import com.goormitrip.goormitrip.payment.exception.PaymentAlreadyCancelledException;
import com.goormitrip.goormitrip.payment.exception.PaymentCancelFailedException;
import com.goormitrip.goormitrip.payment.exception.PaymentFailedException;
import com.goormitrip.goormitrip.payment.exception.PaymentNotFoundException;
import com.goormitrip.goormitrip.payment.exception.UnsupportedMethodException;
import com.goormitrip.goormitrip.payment.repository.TossPaymentRepository;
import com.goormitrip.goormitrip.payment.service.PaymentService;
import com.goormitrip.goormitrip.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private final TossPaymentRepository repository;
	private final WebClient tossWebClient;
	private final ReservationService reservationService;

	@Override
	public ConfirmPaymentResponse confirmPayment(ConfirmPaymentRequest req) {
		String auth = "Basic " + Base64.getEncoder()
			.encodeToString(("test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6:").getBytes());

		String orderId = req.reservationId();

		Map<String, Object> body = Map.of(
			"paymentKey", req.paymentKey(),
			"orderId", orderId,
			"amount", req.amount()
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
		if (paidAmount != req.amount()) {
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
				.tossOrderId(orderId)
				.tossPaymentKey(req.paymentKey())
				.totalAmount(paidAmount)
				.tossPaymentMethod(method)
				.tossPaymentStatus(TossPaymentStatus.PAID)
				.requestedAt(LocalDateTime.now())
				.approvedAt(LocalDateTime.now())
				.build()
		);

		return new ConfirmPaymentResponse(
			orderId,
			"paid",
			method.name(),
			req.amount(),
			saved.getApprovedAt()
		);
	}

	@Override
	public PaymentCancelResponse cancelPayment(PaymentCancelRequest request) {
		String auth = "Basic " + Base64.getEncoder()
			.encodeToString(("test_gsk_docs_OaPz8L5KdmQXkzRz3y47BMw6:").getBytes());

		Long reservationId = Long.parseLong(request.reservationId().replace("A", ""));

		TossPayment payment = repository.findByReservationId(reservationId)
			.orElseThrow(() -> new PaymentNotFoundException());

		if (payment.getTossPaymentStatus() == TossPaymentStatus.REFUNDED ||
			payment.getTossPaymentStatus() == TossPaymentStatus.CANCELED) {
			throw new PaymentAlreadyCancelledException();
		}

		Map<String, Object> body = Map.of(
			"cancelReason", "고객 요청 취소",
			"cancelAmount", payment.getTotalAmount()
		);

		try {
			tossWebClient.post()
				.uri("/v1/payments/" + payment.getTossPaymentKey() + "/cancel")
				.header("Authorization", auth)
				.header("Content-Type", "application/json")
				.bodyValue(body)
				.retrieve()
				.bodyToMono(Void.class)
				.block();
		} catch (Exception e) {
			throw new PaymentCancelFailedException();
		}

		reservationService.cancelReservation(request.reservationId());

		payment.setTossPaymentStatus(TossPaymentStatus.REFUNDED);
		payment.setRefundedAt(LocalDateTime.now());

		TossPayment savedPayment = repository.save(payment);

		return new PaymentCancelResponse(
			request.reservationId(),
			savedPayment.getTotalAmount(),
			savedPayment.getRefundedAt()
		);

	}
}
