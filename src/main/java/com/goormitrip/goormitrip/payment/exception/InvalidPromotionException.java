package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidPromotionException extends BusinessException {
    public InvalidPromotionException() {
        super(PaymentError.INVALID_PROMOTION);
    }
}
