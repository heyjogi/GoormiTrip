package com.goormitrip.goormitrip.payment.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum PaymentError implements ErrorCode {
	INVALID_AMOUNT(HttpStatus.BAD_REQUEST, "PM-001", "결제 금액이 일치하지 않습니다. 금액을 다시 확인해주세요."),
	UNSUPPORTED_METHOD(HttpStatus.BAD_REQUEST, "PM-002", "지원하지 않는 결제 방법입니다. 다시 선택해주세요."),
	INVALID_PROMOTION(HttpStatus.BAD_REQUEST, "PM-003", "존재하지 않는 프로모션 코드입니다."),
	PAYMENT_FAILED(HttpStatus.PAYMENT_REQUIRED, "PM-004", "결제 처리에 실패했습니다. 카드 정보를 확인하거나 다른 결제 수단을 이용해주세요."),
	PAYMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "PM-005", "존재하지 않는 결제 정보입니다."),
	PAYMENT_ALREADY_CANCELLED(HttpStatus.BAD_REQUEST, "PM-006", "이미 취소 처리된 결제입니다."),
	PAYMENT_CANCEL_DEADLINE_EXPIRED(HttpStatus.BAD_REQUEST, "PM-007", "여행 시작 3일전까지만 결제 취소가 가능합니다."),
	PAYMENT_CANCEL_FAILED(HttpStatus.BAD_REQUEST, "PM-008", "결제 취소 처리 중 오류가 발생했습니다. 챗룸으로 문의해주세요.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
