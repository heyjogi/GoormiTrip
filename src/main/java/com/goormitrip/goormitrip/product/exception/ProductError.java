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
	COMPARISON_MINIMUM(HttpStatus.BAD_REQUEST, "P-005", "상품 비교는 2개 이상부터 가능합니다."),
	INVALID_TRAVEL_DATE(HttpStatus.BAD_REQUEST, "P-006", "선택한 날짜는 예약 가능한 날짜가 아닙니다."),
	INVALID_PEOPLE_COUNT(HttpStatus.BAD_REQUEST, "P-007", "이 상품은 %d ~ %d명 까지 예약 가능합니다."),
	RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "P-008", "존재하지 않는 예약 정보입니다."),
	RESERVATION_ALREADY_CANCELLED(HttpStatus.BAD_REQUEST, "P-009", "이미 취소된 예약은 변경할 수 없습니다."),
	RESERVATION_CHANGE_DEADLINE_EXPIRED(HttpStatus.BAD_REQUEST, "P-010", "여행 시작 2일 전까지만 변경 가능합니다."),
	RESERVATION_CANCEL_DEADLINE_EXPIRED(HttpStatus.BAD_REQUEST, "P-011", "여행 시작 3일 전까지만 취소 가능합니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
