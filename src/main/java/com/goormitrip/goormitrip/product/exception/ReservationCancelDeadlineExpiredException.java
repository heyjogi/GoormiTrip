package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationCancelDeadlineExpiredException extends BusinessException {
	public ReservationCancelDeadlineExpiredException() {
		super(
			ProductError.RESERVATION_CANCEL_DEADLINE_EXPIRED);
	}
}
