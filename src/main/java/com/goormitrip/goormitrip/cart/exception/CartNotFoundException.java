package com.goormitrip.goormitrip.cart.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class CartNotFoundException extends BusinessException {
    public CartNotFoundException() {
        super(CartError.CART_NOT_FOUND);
    }

    public CartNotFoundException(String detail) {
        super(CartError.CART_NOT_FOUND, detail);
    }
}