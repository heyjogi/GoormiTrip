package com.goormitrip.goormitrip.reservation.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationResponse extends BaseTimeEntity {
		private String reservationId;
		private Long userId;
		private Long productId;
		private LocalDateTime createdAt;
		private LocalDate travelDate;
		private int peopleCount;
	}
