package com.goormitrip.goormitrip.payment.dto;

public record ConfirmPaymentRequest(
	String paymentKey,
	String reservationId,
	String method,
	String promotionId,
	long amount
) {
}
