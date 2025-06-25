package com.goormitrip.goormitrip.chat.dto.response;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.chat.domain.ChatRoomStatus;
import com.goormitrip.goormitrip.chat.domain.InquiryType;
import com.goormitrip.goormitrip.chat.domain.SenderType;

public interface ChatRoomSummaryProjection {
	Long getRoomId();
	InquiryType getInquiryType();
	ChatRoomStatus getStatus();
	Long getMsgId();
	SenderType getSender();
	String getContent();
	LocalDateTime getSentAt();
}
