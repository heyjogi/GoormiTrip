package com.goormitrip.goormitrip.global.util.exception;

public interface ErrorCode {
	int getStatus();

	String getCode();

	String getMessage();
}