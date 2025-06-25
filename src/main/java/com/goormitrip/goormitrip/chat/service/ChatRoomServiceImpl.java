package com.goormitrip.goormitrip.chat.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.chat.domain.ChatMessage;
import com.goormitrip.goormitrip.chat.domain.ChatRoom;
import com.goormitrip.goormitrip.chat.domain.InquiryType;
import com.goormitrip.goormitrip.chat.domain.SenderType;
import com.goormitrip.goormitrip.chat.dto.request.CreateChatRoomRequest;
import com.goormitrip.goormitrip.chat.dto.request.SendMessageRequest;
import com.goormitrip.goormitrip.chat.dto.response.ChatMessageResponse;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomCreateResponse;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomListResponse;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomSummaryDto;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomSummaryProjection;
import com.goormitrip.goormitrip.chat.dto.response.LastMessageDto;
import com.goormitrip.goormitrip.chat.dto.response.MessageSendResponse;
import com.goormitrip.goormitrip.chat.exception.ChatRoomAccessDeniedException;
import com.goormitrip.goormitrip.chat.repository.ChatMessageRepository;
import com.goormitrip.goormitrip.chat.repository.ChatRoomRepository;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatRoomServiceImpl implements ChatRoomService {

	private final ChatRoomRepository roomRepo;
	private final ChatMessageRepository msgRepo;
	private final UserRepository userRepo;
	private final AdminSelector adminSelector;

	@Override
	@Transactional
	public ChatRoomCreateResponse createRoom(Long userId, CreateChatRoomRequest req) {
		UserEntity user = userRepo.getReferenceById(userId);
		UserEntity admin = adminSelector.pickAdmin();

		ChatRoom room = ChatRoom.of(user, admin, InquiryType.from(req.inquiryType()));
		roomRepo.save(room);

		msgRepo.save(ChatMessage.of(room, SenderType.USER, req.initialMessage()));
		return ChatRoomCreateResponse.from(room);
	}

	@Transactional
	public MessageSendResponse sendMessage(Long roomId, SendMessageRequest req, Long senderId, SenderType who) {
		ChatRoom room = roomRepo.findById(roomId)
			.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 채팅방"));

		boolean permitted = senderId.equals(room.getUser().getId()) ||
			senderId.equals(room.getAdmin().getId());
		if (!permitted) throw new ChatRoomAccessDeniedException();

		ChatMessage msg = msgRepo.save(ChatMessage.of(room, who, req.message()));
		return MessageSendResponse.from(msg);
	}

	public ChatRoomListResponse listRooms(Long userId) {
		List<ChatRoomSummaryProjection> rows = roomRepo.fetchRoomSummaries(userId);
		List<ChatRoomSummaryDto> dto = rows.stream()
			.map(r -> new ChatRoomSummaryDto(
				r.getRoomId(),
				r.getInquiryType(),
				r.getStatus(),
				new LastMessageDto(
					r.getMsgId(),
					r.getSender(),
					r.getContent(),
					r.getSentAt())))
			.toList();
		return new ChatRoomListResponse(dto);
	}

	public Page<ChatMessageResponse> listMessages(Long roomId, Pageable p) {
		return msgRepo.findByRoomIdOrderByCreatedAtAsc(roomId, p)
			.map(ChatMessageResponse::from);
	}
}
