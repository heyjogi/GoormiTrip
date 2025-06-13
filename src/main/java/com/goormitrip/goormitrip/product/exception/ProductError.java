package com.goormitrip.goormitrip.product.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductError implements ErrorCode {
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "Product not found"),
	INVALID_FILTER(HttpStatus.BAD_REQUEST, "P-002", "잘못된 필터 값이 포함되어 있습니다."),
	INVALID_SORT(HttpStatus.BAD_REQUEST, "P-003", "지원하지 않는 정렬 기준입니다."),
	KEYWORD_MISSING(HttpStatus.BAD_REQUEST, "P-004", "검색 키워드를 입력해주세요."),
	COMPARISON_MINIMUM(HttpStatus.BAD_REQUEST, "P-005", "상품 비교는 2개 이상부터 가능합니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
