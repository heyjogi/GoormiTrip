package com.goormitrip.goormitrip.payment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentRequest;
import com.goormitrip.goormitrip.payment.dto.ConfirmPaymentResponse;
import com.goormitrip.goormitrip.payment.dto.PaymentCancelRequest;
import com.goormitrip.goormitrip.payment.dto.PaymentCancelResponse;
import com.goormitrip.goormitrip.payment.service.PaymentService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentService paymentService;

	@PostMapping("/confirm")
	public ResponseEntity<ApiResponse<ConfirmPaymentResponse>> confirm(@RequestBody ConfirmPaymentRequest request) {
		return ApiResponse.ok(paymentService.confirmPayment(request));
	}

	@PutMapping("/{paymentID}/cancel")
	public ResponseEntity<ApiResponse<PaymentCancelResponse>> confirm(
		@PathVariable String paymentId,
		@RequestBody PaymentCancelRequest request) {
		return ApiResponse.ok(paymentService.cancelPayment(request));
	}
}