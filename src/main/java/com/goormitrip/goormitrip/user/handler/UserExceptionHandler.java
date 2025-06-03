package com.goormitrip.goormitrip.user.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.user.exception.EmailDuplicateException;

@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.user")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserExceptionHandler {

	@ExceptionHandler(EmailDuplicateException.class)
	public ResponseEntity<?> handleUserNotFound(final EmailDuplicateException ex) {
		return ApiResponse.error(ex.getErrorCode());
	}
}
