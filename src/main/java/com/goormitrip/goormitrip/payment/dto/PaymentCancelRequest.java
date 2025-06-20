package com.goormitrip.goormitrip.payment.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentCancelRequest {
	@JsonProperty("reservation_id")
	private String reservationId;
	private String reason;
}
