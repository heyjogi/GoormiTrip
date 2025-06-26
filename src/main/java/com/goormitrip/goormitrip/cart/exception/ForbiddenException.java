package com.goormitrip.goormitrip.cart.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ForbiddenException extends BusinessException {
    public ForbiddenException() {
        super(CartError.FORBIDDEN_ACCESS);
    }

    public ForbiddenException(String detail) {
        super(CartError.FORBIDDEN_ACCESS, detail);
    }
}