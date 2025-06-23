package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class PaymentAlreadyCancelledException extends BusinessException {
    public PaymentAlreadyCancelledException() {
        super(PaymentError.PAYMENT_ALREADY_CANCELLED);
    }
}
