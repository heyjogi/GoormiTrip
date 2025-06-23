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
	LOGIN_FAILED(HttpStatus.CONFLICT, "USER-008", "이메일 또는 비밀번호가 잘못되었습니다."),
	SOCIAL_LOGIN_USER_CANNOT_LOGIN(HttpStatus.CONFLICT, "USER-009", "소셜 로그인 사용자는 일반 로그인이 불가능합니다."),
	USER_NOT_FOUND(HttpStatus.NOT_FOUND, "USER-010", "사용자를 찾을 수 없습니다."),
	INVALID_RESET_TOKEN(HttpStatus.NOT_FOUND, "USER-011", "유효하지 않은 비밀번호 재설정 토큰입니다."),
	MAIL_SEND_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "USER-012", "메일 전송에 실패했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
