package com.goormitrip.goormitrip.global.util.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;
import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

public record ApiResponse<T>(
	boolean success,
	T response,
	ApiError error
) {
	private static <T> ApiResponse<T> success(T data) {
		return new ApiResponse<>(true, data, null);
	}

	private static <T> ApiResponse<T> fail(final String message, final HttpStatus status) {
		return new ApiResponse<>(false, null, new ApiError(message, status.value()));
	}

	public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
		return ResponseEntity.ok(success(data));
	}

	public static <T> ResponseEntity<ApiResponse<T>> error(final String message, final HttpStatus status) {
		return ResponseEntity.status(status).body(fail(message, status));
	}

	public static <T> ResponseEntity<ApiResponse<T>> error(final ErrorCode errorCode) {
		return ResponseEntity.status(errorCode.getStatus())
			.body(fail(errorCode.getFormattedMessage(), errorCode.getStatus()));
	}

	public static <T> ResponseEntity<ApiResponse<T>> error(final BusinessException ex) {
		final String message = ex.getDetail() != null
			? ex.getDetail()
			: ex.getErrorCode().getFormattedMessage();
		return ResponseEntity.status(ex.getErrorCode().getStatus())
			.body(fail(message, ex.getErrorCode().getStatus()));
	}
}