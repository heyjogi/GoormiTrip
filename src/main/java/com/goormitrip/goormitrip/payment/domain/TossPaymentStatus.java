package com.goormitrip.goormitrip.payment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TossPaymentStatus {
	READY,
	PAID,
	CANCELED,
	FAILED,
	REFUNDED
}