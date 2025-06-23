package com.goormitrip.goormitrip.chat.exception;

import org.springframework.http.HttpStatus;

import com.goormitrip.goormitrip.global.util.exception.ErrorCode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ChatError implements ErrorCode {
	CHATROOM_ACCESS_DENIED(HttpStatus.FORBIDDEN, "CHAT-001", "해당 채팅방에 접근 권한이 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
