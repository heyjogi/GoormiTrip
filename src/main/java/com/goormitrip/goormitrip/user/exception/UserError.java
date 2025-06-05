package com.goormitrip.goormitrip.user.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum UserError implements ErrorCode {
	EMAIL_DUPLICATE(HttpStatus.CONFLICT, "USER-001", "이미 가입된 회원입니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
