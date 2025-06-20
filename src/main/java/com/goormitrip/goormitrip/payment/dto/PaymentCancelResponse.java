package com.goormitrip.goormitrip.payment.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PaymentCancelResponse {
	private String paymentId;

	@JsonProperty("reservation_id")
	private String reservationId;

	private Long amount;
	private LocalDateTime refundedAt;
	}
