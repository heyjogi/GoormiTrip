package com.goormitrip.goormitrip.reservation.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
	public ProductNotFoundException() {
		super(
			ReservationError.PRODUCT_NOT_FOUND);
	}
}
