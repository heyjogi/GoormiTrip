package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ProductComparisonMinimumException extends BusinessException {
    public ProductComparisonMinimumException() {
        super(ProductError.COMPARISON_MINIMUM);
    }
}
