package com.goormitrip.goormitrip.payment.service;

import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentRequest;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentResponse;
import com.goormitrip.goormitrip.payment.dto.PaymentCancelRequest;
import com.goormitrip.goormitrip.payment.dto.PaymentCancelResponse;

public interface PaymentService {
	ConfirmPaymentResponse confirmPayment(ConfirmPaymentRequest request);

	PaymentCancelResponse cancelPayment(PaymentCancelRequest request);
}