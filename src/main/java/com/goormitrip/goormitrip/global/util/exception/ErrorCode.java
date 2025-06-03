package com.goormitrip.goormitrip.global.util.exception;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
	HttpStatus getStatus();

	String getCode();

	String getMessage();

	default String getFormattedMessage() {
		return String.format("[%s] %s", getCode(), getMessage());
	}
}