package com.goormitrip.goormitrip.wishlist.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class AlreadyWishlistedException extends BusinessException {
     public AlreadyWishlistedException(Long productId) {
        super(WishlistError.ALREADY_WISHLISTED, "상품 ID: " + productId);
    }
}