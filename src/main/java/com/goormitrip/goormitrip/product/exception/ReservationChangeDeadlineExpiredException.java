package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationChangeDeadlineExpiredException extends BusinessException {
	public ReservationChangeDeadlineExpiredException() {
		super(
			ProductError.RESERVATION_CHANGE_DEADLINE_EXPIRED);
	}
}
