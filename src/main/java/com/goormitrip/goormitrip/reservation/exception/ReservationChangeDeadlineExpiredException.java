package com.goormitrip.goormitrip.reservation.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationChangeDeadlineExpiredException extends BusinessException {
	public ReservationChangeDeadlineExpiredException() {
		super(
			ReservationError.RESERVATION_CHANGE_DEADLINE_EXPIRED);
	}
}
