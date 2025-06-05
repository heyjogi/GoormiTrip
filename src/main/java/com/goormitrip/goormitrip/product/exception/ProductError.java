package com.goormitrip.goormitrip.product.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductError implements ErrorCode {
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "Product not found");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
