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
}
