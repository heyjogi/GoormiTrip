package com.goormitrip.goormitrip.payment.service;

import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentRequest;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentResponse;

public interface PaymentService {
	ConfirmPaymentResponse confirmPayment(ConfirmPaymentRequest request);
}