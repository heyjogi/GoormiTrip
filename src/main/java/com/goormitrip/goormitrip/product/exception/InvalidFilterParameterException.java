package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidFilterParameterException extends BusinessException {
	public InvalidFilterParameterException() {
		super(ProductError.INVALID_FILTER);
	}
}
