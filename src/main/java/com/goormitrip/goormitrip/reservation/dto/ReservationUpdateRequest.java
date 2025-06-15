package com.goormitrip.goormitrip.reservation.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ReservationUpdateRequest {
	private LocalDate travelDate;
	private int peopleCount;
}
