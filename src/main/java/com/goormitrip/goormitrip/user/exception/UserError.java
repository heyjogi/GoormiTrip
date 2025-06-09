package com.goormitrip.goormitrip.user.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserError implements ErrorCode {
	EMAIL_DUPLICATE(HttpStatus.CONFLICT, "USER-001", "이미 가입된 회원입니다."),
	PHONE_NOT_VERIFIED(HttpStatus.CONFLICT, "USER-002", "휴대폰 번호 인증을 확인해주세요."),
	INVALID_PASSWORD(HttpStatus.CONFLICT, "USER-003", "비밀번호를 다시 입력하세요."),
	REQUIRED_TERMS_UNCHECKED(HttpStatus.CONFLICT, "USER-004", "필수 약관 동의를 확인해주세요."),
	INVALID_EMAIL_FORMAT(HttpStatus.CONFLICT, "USER-005", "이메일 형식을 확인해주세요."),
	INVALID_PHONE(HttpStatus.CONFLICT, "USER-006", "휴대폰 번호를 확인해주세요."),
	PHONE_VERIFICATION_FAILED(HttpStatus.CONFLICT, "USER-007", "인증에 실패하였습니다."),
	LOGIN_FAILED(HttpStatus.CONFLICT, "USER-008", "이메일 또는 비밀번호가 잘못되었습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
