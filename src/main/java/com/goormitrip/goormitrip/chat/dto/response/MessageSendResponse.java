package com.goormitrip.goormitrip.chat.dto.response;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.chat.domain.ChatMessage;
import com.goormitrip.goormitrip.chat.domain.SenderType;

public record MessageSendResponse(
	Long message_id,
	Long chat_room_id,
	SenderType sender_type,
	Long admin_id,
	String message,
	LocalDateTime created_at
) {
	public static MessageSendResponse from(ChatMessage m) {
		return new MessageSendResponse(
			m.getId(),
			m.getRoom().getId(),
			m.getSender(),
			m.getRoom().getAdmin().getId(),
			m.getContent(),
			m.getCreatedAt()
		);
	}
}
