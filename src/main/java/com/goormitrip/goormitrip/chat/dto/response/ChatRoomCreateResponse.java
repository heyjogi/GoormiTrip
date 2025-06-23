package com.goormitrip.goormitrip.chat.dto.response;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.chat.domain.ChatRoom;

public record ChatRoomCreateResponse(
	Long chat_room_id,
	Long user_id,
	Long admin_id,
	LocalDateTime created_at
) {
	public static ChatRoomCreateResponse from(ChatRoom r) {
		return new ChatRoomCreateResponse(
			r.getId(),
			r.getUser().getId(),
			r.getAdmin().getId(),
			r.getCreatedAt()
		);
	}
}