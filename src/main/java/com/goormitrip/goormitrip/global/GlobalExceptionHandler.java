package com.goormitrip.goormitrip.global;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
@Order(Ordered.LOWEST_PRECEDENCE)
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ResponseEntity<?> handleBusiness(BusinessException ex) {
		return ApiResponse.error(ex.getErrorCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidation(MethodArgumentNotValidException ex) {
		String msg = ex.getBindingResult().getFieldErrors()
			.stream()
			.map(FieldError::getDefaultMessage)
			.findFirst()
			.orElse("잘못된 입력입니다.");
		return ApiResponse.error(msg, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<Void>> handleUnknown(RuntimeException ex) {
		log.error("Unhandled exception", ex);
		return ApiResponse.error("알 수 없는 서버 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
