package com.goormitrip.goormitrip.chat.dto.response;

import com.goormitrip.goormitrip.chat.domain.ChatRoomStatus;
import com.goormitrip.goormitrip.chat.domain.InquiryType;

public record ChatRoomSummaryDto(
	Long id,
	InquiryType inquiryType,
	ChatRoomStatus status,
	LastMessageDto lastMessage
) { }
