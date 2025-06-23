package com.goormitrip.goormitrip.product.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ProductError implements ErrorCode {
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "P-001", "Product not found"),
	INVALID_REQUEST_PARAMETER(HttpStatus.BAD_REQUEST, "P-002", "유효하지 않은 요청 값입니다. 다시 확인해주세요."),
	KEYWORD_MISSING(HttpStatus.BAD_REQUEST, "P-003", "검색 키워드를 입력해주세요."),
	COMPARISON_MINIMUM(HttpStatus.BAD_REQUEST, "P-004", "상품 비교는 2개 이상부터 가능합니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;

	public ErrorCode withMessage(String customMessage) {
		return new ErrorCode() {
			@Override
			public HttpStatus getStatus() {
				return status;
			}

			@Override
			public String getCode() {
				return code;
			}

			@Override
			public String getMessage() {
				return customMessage;
			}
		};
	}
}
