package com.goormitrip.goormitrip.reservation.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidPeopleCountException extends BusinessException {
	public InvalidPeopleCountException(int min, int max) {
		super(
			ReservationError.INVALID_PEOPLE_COUNT,
			String.format(ReservationError.INVALID_PEOPLE_COUNT.getMessage(), min, max)
		);
	}
}
