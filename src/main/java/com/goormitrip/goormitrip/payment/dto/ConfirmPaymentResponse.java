package com.goormitrip.goormitrip.payment.dto;

import java.time.LocalDateTime;

public record ConfirmPaymentResponse(
	String reservationId,
	String status,
	String method,
	Long amount,
	LocalDateTime paidAt
) {
}