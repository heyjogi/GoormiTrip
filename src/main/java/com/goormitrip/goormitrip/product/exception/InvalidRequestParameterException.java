package com.goormitrip.goormitrip.product.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidRequestParameterException extends BusinessException {
    public InvalidRequestParameterException(String message) {
        super(ProductError.INVALID_REQUEST_PARAMETER, message);
    }
}