package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class PaymentFailedException extends BusinessException {
    public PaymentFailedException() {
        super(PaymentError.PAYMENT_FAILED);
    }
}
