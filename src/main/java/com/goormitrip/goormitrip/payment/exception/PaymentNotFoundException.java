package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class PaymentNotFoundException extends BusinessException {
    public PaymentNotFoundException() {
        super(PaymentError.PAYMENT_NOT_FOUND);
    }
}
