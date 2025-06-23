package com.goormitrip.goormitrip.cart.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter

public enum CartError implements ErrorCode {
	CART_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "C-001", "장바구니 항목을 찾을 수 없습니다."),
	CART_NOT_FOUND(HttpStatus.NOT_FOUND, "C-002", "장바구니를 찾을 수 없습니다."),
	FORBIDDEN_ACCESS(HttpStatus.FORBIDDEN, "C-003", "접근 권한이 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
