package com.goormitrip.goormitrip.user.domain;

import java.time.Duration;
import java.time.Instant;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "password_reset_tokens",
	indexes = @Index(name = "idx_token", columnList = "token", unique = true))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PasswordResetToken extends BaseTimeEntity {

	private static final Duration EXPIRE_IN = Duration.ofMinutes(15);

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 128, unique = true)
	private String token;

	@Column(nullable = false)
	private Instant expiresAt;

	@Column(nullable = false)
	private boolean used = false;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public static PasswordResetToken create(UserEntity user, String token) {
		PasswordResetToken t = new PasswordResetToken();
		t.user       = user;
		t.token      = token;
		t.expiresAt  = Instant.now().plus(EXPIRE_IN);
		return t;
	}

	public void markUsed()          { this.used = true; }
	public boolean isExpired()      { return Instant.now().isAfter(expiresAt); }
}
