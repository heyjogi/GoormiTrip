package com.goormitrip.goormitrip.reservation.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationNotFoundException extends BusinessException {
	public ReservationNotFoundException() {
		super(
			ReservationError.RESERVATION_NOT_FOUND);
	}
}
