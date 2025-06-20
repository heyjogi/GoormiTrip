package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class UnsupportedMethodException extends BusinessException {
    public UnsupportedMethodException() {
        super(PaymentError.UNSUPPORTED_METHOD);
    }
}
