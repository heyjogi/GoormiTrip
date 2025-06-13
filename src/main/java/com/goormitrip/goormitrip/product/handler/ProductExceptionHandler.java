package com.goormitrip.goormitrip.product.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.product.exception.ProductNotFoundException;
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
}
