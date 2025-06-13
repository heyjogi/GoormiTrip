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
import com.goormitrip.goormitrip.product.exception.InvalidFilterParameterException;
import com.goormitrip.goormitrip.product.exception.InvalidSortParameterException;
import com.goormitrip.goormitrip.product.exception.KeywordRequiredException;
import com.goormitrip.goormitrip.product.exception.ProductComparisonMinimumException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.product")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProductExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
		log.warn("Product not found. detail={}", ex.getDetail());
		return ResponseEntity
				.status(ex.getErrorCode().getStatus())
				.body(ApiResponse.error(ex));
	}

	@ExceptionHandler(InvalidFilterParameterException.class)
	public ResponseEntity<?> handleInvalidFilter(InvalidFilterParameterException ex) {
		log.warn("Invalid filter parameter. detail={}", ex.getDetail());
		return ResponseEntity
				.status(ex.getErrorCode().getStatus())
				.body(ApiResponse.error(ex));
	}

	@ExceptionHandler(InvalidSortParameterException.class)
	public ResponseEntity<?> handleInvalidSort(InvalidSortParameterException ex) {
		log.warn("Invalid sort parameter. detail={}", ex.getDetail());
		return ResponseEntity
				.status(ex.getErrorCode().getStatus())
				.body(ApiResponse.error(ex));
	}

	@ExceptionHandler(KeywordRequiredException.class)
	public ResponseEntity<?> handleKeywordMissing(KeywordRequiredException ex) {
		log.warn("Missing keyword. detail={}", ex.getDetail());
		return ResponseEntity
				.status(ex.getErrorCode().getStatus())
				.body(ApiResponse.error(ex));
	}

	@ExceptionHandler(ProductComparisonMinimumException.class)
	public ResponseEntity<?> handleComparisonMinimum(ProductComparisonMinimumException ex) {
		log.warn("Comparison requires at least 2 IDs. detail={}", ex.getDetail());
		return ResponseEntity
				.status(ex.getErrorCode().getStatus())
				.body(ApiResponse.error(ex));
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
