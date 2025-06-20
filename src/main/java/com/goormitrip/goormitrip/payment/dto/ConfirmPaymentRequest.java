package com.goormitrip.goormitrip.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmPaymentRequest {
	private String paymentKey;
	private String orderId;
	private long amount;
}
