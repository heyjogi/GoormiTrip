package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidTravelDateException extends BusinessException {
	public InvalidTravelDateException(long id) {
		super(
			ProductError.INVALID_TRAVEL_DATE
		);
	}
}
