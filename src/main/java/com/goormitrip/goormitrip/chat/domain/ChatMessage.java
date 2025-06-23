package com.goormitrip.goormitrip.chat.domain;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "chat_messages",
	indexes = @Index(name = "idx_room_created", columnList = "room_id, created_at"))
@Getter
public class ChatMessage extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "room_id")
	private ChatRoom room;

	@Column(nullable = false, length = 2000)   private String content;

	@Enumerated(EnumType.STRING) @Column(nullable = false)
	private SenderType sender;

	public static ChatMessage of(ChatRoom room, SenderType s, String c) {
		ChatMessage m = new ChatMessage();
		m.room = room;
		m.sender = s;
		m.content = c;
		return m;
	}
}
