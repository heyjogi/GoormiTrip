package com.goormitrip.goormitrip.reservation.dto;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationCancelResponse extends BaseTimeEntity {
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
		private LocalDateTime updatedAt;
	}
}
