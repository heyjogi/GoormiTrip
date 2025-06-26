package com.goormitrip.goormitrip.wishlist.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter

public enum WishlistError implements ErrorCode {
	ALREADY_WISHLISTED(HttpStatus.CONFLICT, "W-001", "이미 찜 목록에 추가된 상품입니다."),
    WISHLIST_NOT_FOUND(HttpStatus.NOT_FOUND, "W-002", "찜 목록에 존재하지 않습니다.");
	
	private final HttpStatus status;
	private final String code;
	private final String message;
}
