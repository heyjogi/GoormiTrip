package com.goormitrip.goormitrip.review.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewError implements ErrorCode {
	DUPLICATE_REVIEW(HttpStatus.BAD_REQUEST, "REVIEW-001", "이미 작성한 리뷰가 있습니다."),;

	private final HttpStatus status;
	private final String code;
	private final String message;
}
