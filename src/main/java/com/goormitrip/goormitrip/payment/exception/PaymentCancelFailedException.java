package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class PaymentCancelFailedException extends BusinessException {
    public PaymentCancelFailedException() {
        super(PaymentError.PAYMENT_CANCEL_FAILED);
    }
}
