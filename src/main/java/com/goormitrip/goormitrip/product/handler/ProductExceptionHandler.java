package com.goormitrip.goormitrip.product.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.product.exception.InvalidPeopleCountException;
import com.goormitrip.goormitrip.product.exception.InvalidTravelDateException;
import com.goormitrip.goormitrip.product.exception.ProductNotFoundException;
import com.goormitrip.goormitrip.product.exception.ReservationAlreadyCancelledException;
import com.goormitrip.goormitrip.product.exception.ReservationCancelDeadlineExpiredException;
import com.goormitrip.goormitrip.product.exception.ReservationChangeDeadlineExpiredException;
import com.goormitrip.goormitrip.product.exception.ReservationNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.product")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProductExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
		log.warn("Product not found. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(InvalidTravelDateException.class)
	public ResponseEntity<?> handleInvalidTravelDate(InvalidTravelDateException ex) {
		log.warn("Invalid travel date. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(InvalidPeopleCountException.class)
	public ResponseEntity<?> handleInvalidPeopleCount(InvalidPeopleCountException ex) {
		log.warn("Invalid people count. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(ReservationAlreadyCancelledException.class)
	public ResponseEntity<?> handleReservationAlreadyCancelled(ReservationAlreadyCancelledException ex) {
		log.warn("Reservation already cancelled. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(ReservationChangeDeadlineExpiredException.class)
	public ResponseEntity<?> handleReservationChangeDeadlineExpired(ReservationChangeDeadlineExpiredException ex) {
		log.warn("Reservation change deadline expired. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(ReservationNotFoundException.class)
	public ResponseEntity<?> handleReservationNotFound(ReservationNotFoundException ex) {
		log.warn("Reservation not found. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}

	@ExceptionHandler(ReservationCancelDeadlineExpiredException.class)
	public ResponseEntity<?> handleReservationCancelDeadlineExpired(ReservationCancelDeadlineExpiredException ex) {
		log.warn("Reservation cancel deadline expired. detail={}", ex.getDetail());
		return ApiResponse.error(ex);
	}
}
