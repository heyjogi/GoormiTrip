package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class PaymentCancelDeadlineExpiredException extends BusinessException {
    public PaymentCancelDeadlineExpiredException() {
        super(PaymentError.PAYMENT_CANCEL_DEADLINE_EXPIRED);
    }
}
