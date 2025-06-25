package com.goormitrip.goormitrip.chat.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.chat.domain.SenderType;
import com.goormitrip.goormitrip.chat.dto.request.CreateChatRoomRequest;
import com.goormitrip.goormitrip.chat.dto.request.SendMessageRequest;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomListResponse;
import com.goormitrip.goormitrip.chat.dto.response.MessageSendResponse;
import com.goormitrip.goormitrip.chat.service.ChatRoomService;
import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.user.domain.UserRole;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/chats")
@RequiredArgsConstructor
public class ChatRoomController {

	private final ChatRoomService chatSvc;

	@PostMapping
	public ResponseEntity<?> createRoom(@AuthenticationPrincipal CustomUserDetails u,
		@Valid @RequestBody CreateChatRoomRequest req) {
		final var res = chatSvc.createRoom(u.getId(), req);
		return ApiResponse.ok(res);
	}

	@PostMapping("/{id}/messages")
	public ResponseEntity<?> sendMessage(@AuthenticationPrincipal CustomUserDetails u,
		@PathVariable Long id,
		@Valid @RequestBody SendMessageRequest req) {
		SenderType type = (u.getRole() == UserRole.ADMIN)
			? SenderType.ADMIN : SenderType.USER;

		MessageSendResponse res = chatSvc.sendMessage(id, req, u.getId(), type);
		return ApiResponse.ok(res);
	}

	@GetMapping("/mychat")
	public ResponseEntity<?> listRooms(@AuthenticationPrincipal CustomUserDetails u) {
		final ChatRoomListResponse res = chatSvc.listRooms(u.isAdmin() ? null : u.getId());
		return ApiResponse.ok(res);
	}

	@GetMapping("/{id}/messages")
	public ResponseEntity<?> listMessages(@PathVariable Long id,
		@PageableDefault(size = 40) Pageable p) {
		return ApiResponse.ok(chatSvc.listMessages(id, p));
	}
}