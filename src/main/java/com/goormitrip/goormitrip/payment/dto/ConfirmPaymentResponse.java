package com.goormitrip.goormitrip.payment.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ConfirmPaymentResponse {
	private boolean success;
	private String message;
	private String paymentId;
	private String status;
	private LocalDateTime approvedAt;
}