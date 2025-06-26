package com.goormitrip.goormitrip.chat.exception;

import com.goormitrip.goormitrip.global.util.exception.BusinessException;

public class ChatRoomAccessDeniedException extends BusinessException {
	public ChatRoomAccessDeniedException() {
		super(ChatError.CHATROOM_ACCESS_DENIED);
	}
}
