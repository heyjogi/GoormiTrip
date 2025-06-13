package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ProductNotFoundException extends BusinessException {
	public ProductNotFoundException() {
		super(
			ProductError.PRODUCT_NOT_FOUND);
	}
}
