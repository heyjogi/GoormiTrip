package com.goormitrip.goormitrip.cart.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class CartItemNotFoundException extends BusinessException {
    public CartItemNotFoundException(Long id) {
        super(CartError.CART_ITEM_NOT_FOUND, "장바구니 아이템 ID: " + id);
    }
}