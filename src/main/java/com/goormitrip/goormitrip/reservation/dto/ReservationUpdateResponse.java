package com.goormitrip.goormitrip.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationUpdateResponse {
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
		private LocalDateTime updatedAt;
		private LocalDate travelDate;
		private int peopleCount;
	}
}
