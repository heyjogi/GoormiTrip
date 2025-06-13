package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ReservationAlreadyCancelledException extends BusinessException {
	public ReservationAlreadyCancelledException() {
		super(
			ProductError.RESERVATION_ALREADY_CANCELLED);
	}
}
