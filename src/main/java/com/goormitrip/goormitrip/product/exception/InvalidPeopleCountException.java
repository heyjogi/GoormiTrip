package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidPeopleCountException extends BusinessException {
	public InvalidPeopleCountException(int min, int max) {
		super(
			ProductError.INVALID_PEOPLE_COUNT,
			String.format(ProductError.INVALID_PEOPLE_COUNT.getMessage(), min, max)
		);
	}
}
