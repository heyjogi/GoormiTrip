package com.goormitrip.goormitrip.chat.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.chat.domain.ChatMessage;

public interface ChatMessageRepository
	extends JpaRepository<ChatMessage, Long> {

	Page<ChatMessage> findByRoomIdOrderByCreatedAtAsc(Long roomId, Pageable page);
}