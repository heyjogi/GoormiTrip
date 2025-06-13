package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationNotFoundException extends BusinessException {
	public ReservationNotFoundException() {
		super(
			ProductError.RESERVATION_NOT_FOUND);
	}
}
