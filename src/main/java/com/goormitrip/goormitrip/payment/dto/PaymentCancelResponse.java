package com.goormitrip.goormitrip.payment.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentCancelResponse(
	@JsonProperty("reservation_id")
	String reservationId,

	Long amount,
	LocalDateTime refundedAt
) {
}
