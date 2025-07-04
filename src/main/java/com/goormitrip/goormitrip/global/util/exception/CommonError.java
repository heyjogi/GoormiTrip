package com.goormitrip.goormitrip.global.util.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommonError implements ErrorCode {
	RESOURCE_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMON-001", "요청하신 리소스가 존재하지 않습니다"),
	FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON-002", "접근 권한이 없습니다"),;

	private final HttpStatus status;
	private final String code;
	private final String message;
}
