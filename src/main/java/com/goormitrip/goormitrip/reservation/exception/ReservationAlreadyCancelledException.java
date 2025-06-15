package com.goormitrip.goormitrip.reservation.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationAlreadyCancelledException extends BusinessException {
	public ReservationAlreadyCancelledException() {
		super(
			ReservationError.RESERVATION_ALREADY_CANCELLED);
	}
}
