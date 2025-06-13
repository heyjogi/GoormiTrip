package com.goormitrip.goormitrip.product.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationResponse extends BaseTimeEntity {
	private boolean success;
	private ResponseInfo response;
	private Data data;

	@Getter
	@Builder
	public static class ResponseInfo {
		private String status;
		private String message;
	}

	@Getter
	@Builder
	public static class Data {
		private String reservationId;
		private Long userId;
		private Long productId;
		private LocalDateTime createdAt;
		private LocalDate travelDate;
		private int peopleCount;
	}
}
