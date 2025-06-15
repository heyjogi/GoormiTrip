package com.goormitrip.goormitrip.reservation.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ReservationError implements ErrorCode {
	PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "R-001", "Product not found"),
	INVALID_TRAVEL_DATE(HttpStatus.BAD_REQUEST, "R-002", "선택한 날짜는 예약 가능한 날짜가 아닙니다."),
	INVALID_PEOPLE_COUNT(HttpStatus.BAD_REQUEST, "R-003", "이 상품은 %d ~ %d명 까지 예약 가능합니다."),
	RESERVATION_NOT_FOUND(HttpStatus.NOT_FOUND, "R-004", "존재하지 않는 예약 정보입니다."),
	RESERVATION_ALREADY_CANCELLED(HttpStatus.BAD_REQUEST, "R-005", "이미 취소된 예약은 변경할 수 없습니다."),
	RESERVATION_CHANGE_DEADLINE_EXPIRED(HttpStatus.BAD_REQUEST, "R-006", "여행 시작 2일 전까지만 변경 가능합니다."),
	RESERVATION_CANCEL_DEADLINE_EXPIRED(HttpStatus.BAD_REQUEST, "R-007", "여행 시작 3일 전까지만 취소 가능합니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
