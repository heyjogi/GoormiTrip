package com.goormitrip.goormitrip.product.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductError implements ErrorCode {
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "예약할 수 없는 상품입니다."),
	INVALID_TRAVEL_DATE(HttpStatus.NOT_FOUND, "P-002", "선택한 날짜는 예약 가능한 날짜가 아닙니다."),
	INVALID_PEOPLE_COUNT(HttpStatus.NOT_FOUND, "P-003", "이 상품은 %d ~ %d명 까지 예약 가능합니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
