package com.goormitrip.goormitrip.chat.domain;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;
import com.goormitrip.goormitrip.user.domain.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "chat_rooms")
@Getter
public class ChatRoom extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING) @Column(nullable = false)
	private InquiryType inquiryType;

	@Enumerated(EnumType.STRING) @Column(nullable = false)
	private ChatRoomStatus status = ChatRoomStatus.ACTIVE;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "admin_id", nullable = false)
	private UserEntity admin;

	public static ChatRoom of(UserEntity user, UserEntity admin, InquiryType inquiryType) {
		ChatRoom room = new ChatRoom();
		room.user = user;
		room.admin = admin;
		room.inquiryType = inquiryType;
		return room;
	}

	public void markAnswered() { this.status = ChatRoomStatus.ANSWERED; }
}