package com.goormitrip.goormitrip.oauth.domain;

import java.time.LocalDateTime;

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
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "oauth_account",
	uniqueConstraints = @UniqueConstraint(
		name = "uk_provider_uid", columnNames = {"provider", "provider_user_id"}))
public class OAuthAccount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, length = 20)
	private SocialProvider provider;

	@Column(name = "provider_user_id", nullable = false, length = 128)
	private String providerUserId;

	@Column(length = 320)
	private String email;

	private LocalDateTime createdAt;

	/* 연관관계 주인: N : 1 */
	@ManyToOne(fetch = FetchType.EAGER, optional = false)
	@JoinColumn(name = "user_id")
	private UserEntity user;
}