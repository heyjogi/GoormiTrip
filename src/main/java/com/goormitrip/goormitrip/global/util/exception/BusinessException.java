package com.goormitrip.goormitrip.global.util.exception;

import lombok.Getter;

@Getter
public abstract class BusinessException extends RuntimeException {
	private final ErrorCode errorCode;
	private final String detail;

	protected BusinessException(ErrorCode ec) {
		this(ec, null);
	}

	protected BusinessException(ErrorCode ec, String detail) {
		super(detail == null ? ec.getFormattedMessage() : detail);
		this.errorCode = ec;
		this.detail = detail;
	}
}