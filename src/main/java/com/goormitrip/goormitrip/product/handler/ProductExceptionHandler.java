package com.goormitrip.goormitrip.product.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.product.exception.ProductNotFoundException;

@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.product")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ProductExceptionHandler {
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<?> handleProductNotFound(ProductNotFoundException ex) {
		return ApiResponse.error(ex);
	}
}
