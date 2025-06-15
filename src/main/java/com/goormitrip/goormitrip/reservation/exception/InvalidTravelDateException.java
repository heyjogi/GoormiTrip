package com.goormitrip.goormitrip.reservation.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidTravelDateException extends BusinessException {
	public InvalidTravelDateException() {
		super(
			ReservationError.INVALID_TRAVEL_DATE
		);
	}
}
