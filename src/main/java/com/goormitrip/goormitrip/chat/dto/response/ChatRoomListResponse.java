package com.goormitrip.goormitrip.chat.dto.response;

import java.util.List;

public record ChatRoomListResponse(
	List<ChatRoomSummaryDto> chat_rooms
) {
}
