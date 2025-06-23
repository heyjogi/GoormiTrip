package com.goormitrip.goormitrip.wishlist.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class WishlistNotFoundException extends BusinessException {
    public WishlistNotFoundException(Long productId) {
        super(WishlistError.WISHLIST_NOT_FOUND, "상품 ID: " + productId);
    }
}