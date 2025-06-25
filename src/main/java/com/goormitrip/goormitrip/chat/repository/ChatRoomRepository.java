package com.goormitrip.goormitrip.chat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.goormitrip.goormitrip.chat.domain.ChatRoom;
import com.goormitrip.goormitrip.chat.dto.response.ChatRoomSummaryProjection;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
	@Query("""
	SELECT  r.id           AS roomId,
			r.inquiryType  AS inquiryType,
			r.status       AS status,
			m.id           AS msgId,
			m.sender       AS sender,
			m.content      AS content,
			m.createdAt    AS sentAt
	 FROM ChatRoom r
	 LEFT JOIN ChatMessage m
			ON m.id = ( SELECT m2.id FROM ChatMessage m2
						WHERE m2.room = r
						ORDER BY m2.createdAt DESC
						LIMIT 1 )
	 WHERE (:userId IS NULL OR r.user.id = :userId)
	 ORDER BY sentAt DESC
	 """)
	List<ChatRoomSummaryProjection> fetchRoomSummaries(Long userId);
}