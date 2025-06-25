package com.goormitrip.goormitrip.chat.dto.response;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.chat.domain.SenderType;

public record LastMessageDto(
	Long message_id,
	SenderType sender_type,
	String message,
	LocalDateTime sent_at) {
}
