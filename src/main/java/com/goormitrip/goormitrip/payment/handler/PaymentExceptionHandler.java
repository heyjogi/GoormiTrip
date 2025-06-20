package com.goormitrip.goormitrip.payment.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.payment.exception.InvalidAmountException;
import com.goormitrip.goormitrip.payment.exception.InvalidPromotionException;
import com.goormitrip.goormitrip.payment.exception.PaymentAlreadyCancelledException;
import com.goormitrip.goormitrip.payment.exception.PaymentCancelDeadlineExpiredException;
import com.goormitrip.goormitrip.payment.exception.PaymentCancelFailedException;
import com.goormitrip.goormitrip.payment.exception.PaymentFailedException;
import com.goormitrip.goormitrip.payment.exception.PaymentNotFoundException;
import com.goormitrip.goormitrip.payment.exception.UnsupportedMethodException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.product")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PaymentExceptionHandler {
	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<?> handleInvalidAmount(InvalidAmountException ex) {
		log.warn("Invalid amount. detail={}", ex.getDetail());
		return ResponseEntity
				.status(ex.getErrorCode().getStatus())
				.body(ApiResponse.error(ex));
	}

	@ExceptionHandler(UnsupportedMethodException.class)
	public ResponseEntity<?> handleUnsupportedMethod(UnsupportedMethodException ex) {
		log.warn("Unsupported payment method. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(InvalidPromotionException.class)
	public ResponseEntity<?> handleInvalidPromotion(InvalidPromotionException ex) {
		log.warn("Invalid promotion. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(PaymentFailedException.class)
	public ResponseEntity<?> handlePaymentFailed(PaymentFailedException ex) {
		log.warn("Payment failed. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(PaymentNotFoundException.class)
	public ResponseEntity<?> handlePaymentNotFound(PaymentNotFoundException ex) {
		log.warn("Payment not found. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(PaymentAlreadyCancelledException.class)
	public ResponseEntity<?> handlePaymentAlreadyCancelled(PaymentAlreadyCancelledException ex) {
		log.warn("Payment already cancelled. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(PaymentCancelDeadlineExpiredException.class)
	public ResponseEntity<?> handlePaymentCancelDeadlineExpired(PaymentCancelDeadlineExpiredException ex) {
		log.warn("Payment cancel deadline expired. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}
	@ExceptionHandler(PaymentCancelFailedException.class)
	public ResponseEntity<?> handlePaymentCancelFailed(PaymentCancelFailedException ex) {
		log.warn("Payment cancel failed. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}
}
