package com.goormitrip.goormitrip.reservation.dto;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ReservationCancelResponse extends BaseTimeEntity {
		private String reservationId;
		private LocalDateTime updatedAt;
	}
