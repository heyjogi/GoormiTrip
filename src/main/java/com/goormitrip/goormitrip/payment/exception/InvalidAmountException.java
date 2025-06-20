package com.goormitrip.goormitrip.payment.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class InvalidAmountException extends BusinessException {
    public InvalidAmountException() {
        super(PaymentError.INVALID_AMOUNT);
    }
}
