package com.goormitrip.goormitrip.product.dto;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class ReservationUpdateRequest {
	private LocalDate travelDate;
	private int peopleCount;
}
