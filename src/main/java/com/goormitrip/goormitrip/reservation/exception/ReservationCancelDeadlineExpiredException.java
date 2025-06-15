package com.goormitrip.goormitrip.reservation.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationCancelDeadlineExpiredException extends BusinessException {
	public ReservationCancelDeadlineExpiredException() {
		super(
			ReservationError.RESERVATION_CANCEL_DEADLINE_EXPIRED);
	}
}
