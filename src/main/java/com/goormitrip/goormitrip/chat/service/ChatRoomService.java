package com.goormitrip.goormitrip.chat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.goormitrip.goormitrip.chat.domain.SenderType;
import com.goormitrip.goormitrip.chat.dto.request.CreateChatRoomRequest;
import com.goormitrip.goormitrip.chat.dto.request.SendMessageRequest;
import com.goormitrip.goormitrip.chat.dto.response.ChatMessageResponse;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomCreateResponse;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomListResponse;
import com.goormitrip.goormitrip.chat.dto.response.MessageSendResponse;

public interface ChatRoomService {
	ChatRoomCreateResponse createRoom(Long userId, CreateChatRoomRequest req);
	MessageSendResponse sendMessage(Long roomId, SendMessageRequest req, Long senderId, SenderType who);
	ChatRoomListResponse listRooms(Long userId);
	Page<ChatMessageResponse> listMessages(Long roomId, Pageable p);
}
