package com.goormitrip.goormitrip.chat.dto.response;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.chat.domain.ChatMessage;
import com.goormitrip.goormitrip.chat.domain.SenderType;

public record ChatMessageResponse(
	Long id,
	SenderType sender,
	String content,
	LocalDateTime sentAt
) {
	public static ChatMessageResponse from(ChatMessage m) {
		return new ChatMessageResponse(m.getId(), m.getSender(), m.getContent(), m.getCreatedAt());
	}
}