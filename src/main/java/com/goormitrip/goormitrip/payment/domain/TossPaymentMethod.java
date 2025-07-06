package com.goormitrip.goormitrip.payment.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum TossPaymentMethod {
	CARD,
	VIRTUAL_ACCOUNT,
	KAKAOPAY,
	TOSS_PAY
}