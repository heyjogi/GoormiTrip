package com.goormitrip.goormitrip.global.util.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
	private final ErrorCode errorCode;

	protected BusinessException(final ErrorCode errorCode) {
		super(errorCode.message());
		this.errorCode = errorCode;
	}
}