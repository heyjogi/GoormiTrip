package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidSortParameterException extends BusinessException {
    public InvalidSortParameterException() {
        super(ProductError.INVALID_SORT);
    }
}