package com.goormitrip.goormitrip.oauth;

import java.time.LocalDateTime;

import com.goormitrip.goormitrip.user.domain.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "oauth_account",
	uniqueConstraints = @UniqueConstraint(
		name = "uk_provider_uid", columnNames = {"provider", "provider_user_id"}))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class OAuthAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/** 공급자(GOOGLE·NAVER·KAKAO) */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private SocialProvider provider;

	/** 공급자 내부 사용자 식별자 */
	@Column(name = "provider_user_id", nullable = false, length = 128)
	private String providerUserId;

	@Column(length = 320)
	private String email;

	@Column(name = "created_at", nullable = false,
		columnDefinition = "timestamp default current_timestamp")
	private LocalDateTime createdAt;

	/** N:1 사용자 */
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "fk_oauth_user"))
	private UserEntity user;
}