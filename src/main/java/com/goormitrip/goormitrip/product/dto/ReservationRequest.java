package com.goormitrip.goormitrip.product.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class ReservationRequest {
	private Long productId;
	private LocalDate travelDate;
	private int peopleCount;
}
