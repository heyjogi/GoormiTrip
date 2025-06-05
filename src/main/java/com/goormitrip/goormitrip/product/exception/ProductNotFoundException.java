package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
	public ProductNotFoundException(long id) {
		super(
			ProductError.PRODUCT_NOT_FOUND,
			String.format("%s with id: %d",
				ProductError.PRODUCT_NOT_FOUND.getFormattedMessage(), id)
		);
	}
}
