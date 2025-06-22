package com.goormitrip.goormitrip.payment.service.impl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.payment.domain.TossPayment;
import com.goormitrip.goormitrip.payment.domain.TossPaymentMethod;
import com.goormitrip.goormitrip.payment.domain.TossPaymentStatus;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentRequest;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentResponse;
import com.goormitrip.goormitrip.payment.dto.PaymentCancelRequest;
import com.goormitrip.goormitrip.payment.dto.PaymentCancelResponse;
import com.goormitrip.goormitrip.payment.exception.InvalidAmountException;
import com.goormitrip.goormitrip.payment.exception.PaymentAlreadyCancelledException;
import com.goormitrip.goormitrip.payment.exception.PaymentFailedException;
import com.goormitrip.goormitrip.payment.exception.PaymentNotFoundException;
import com.goormitrip.goormitrip.payment.exception.UnsupportedMethodException;
import com.goormitrip.goormitrip.payment.external.TossPaymentClient;
import com.goormitrip.goormitrip.payment.repository.TossPaymentRepository;
import com.goormitrip.goormitrip.payment.service.PaymentService;
import com.goormitrip.goormitrip.reservation.service.ReservationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class PaymentServiceImpl implements PaymentService {

	private final TossPaymentRepository repository;
	private final TossPaymentClient tossPaymentClient;
	private final ReservationService reservationService;

	@Override
	public ConfirmPaymentResponse confirmPayment(ConfirmPaymentRequest req) {
		String orderId = req.reservationId();

		Map<String, Object> tossRes = tossPaymentClient.confirmPayment(
			req.paymentKey(), orderId, req.amount()
		);
		validatePaymentResponse(tossRes, req.amount());

		TossPaymentMethod method = parsePaymentMethod(tossRes.get("method"));
		TossPayment saved = saveConfirmedPayment(req, orderId, method);

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
		Long reservationId = parseReservationId(request.reservationId());

		TossPayment payment = repository.findByReservationId(reservationId)
			.orElseThrow(PaymentNotFoundException::new);

		if (isAlreadyCancelled(payment)) {
			throw new PaymentAlreadyCancelledException();
		}

		tossPaymentClient.cancelPayment(payment.getTossPaymentKey(), payment.getTotalAmount());
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

	private void validatePaymentResponse(Map<String, Object> res, long expectedAmount) {
		if (res == null || !"DONE".equalsIgnoreCase(String.valueOf(res.get("status")))) {
			throw new PaymentFailedException();
		}
		long actualAmount = ((Number)res.get("amount")).longValue();
		if (actualAmount != expectedAmount) {
			throw new InvalidAmountException();
		}
	}

	private TossPaymentMethod parsePaymentMethod(Object methodObj) {
		try {
			return TossPaymentMethod.valueOf(methodObj.toString().toUpperCase());
		} catch (Exception e) {
			throw new UnsupportedMethodException();
		}
	}

	private TossPayment saveConfirmedPayment(ConfirmPaymentRequest req, String orderId, TossPaymentMethod method) {
		return repository.save(
			TossPayment.builder()
				.paymentId(UUID.randomUUID().toString().getBytes())
				.tossOrderId(orderId)
				.tossPaymentKey(req.paymentKey())
				.totalAmount(req.amount())
				.tossPaymentMethod(method)
				.tossPaymentStatus(TossPaymentStatus.PAID)
				.requestedAt(LocalDateTime.now())
				.approvedAt(LocalDateTime.now())
				.build()
		);
	}

	private boolean isAlreadyCancelled(TossPayment payment) {
		return payment.getTossPaymentStatus() == TossPaymentStatus.REFUNDED ||
			payment.getTossPaymentStatus() == TossPaymentStatus.CANCELED;
	}

	private Long parseReservationId(String reservationId) {
		return Long.parseLong(reservationId.replace("A", ""));
	}

}
