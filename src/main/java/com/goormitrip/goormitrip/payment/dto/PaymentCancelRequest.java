package com.goormitrip.goormitrip.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PaymentCancelRequest(
	@JsonProperty("reservation_id")
	String reservationId,
	String reason
) {
}
