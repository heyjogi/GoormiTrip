package com.goormitrip.goormitrip.oauth.domain;

import java.time.Duration;
import java.time.LocalDateTime;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;
import com.goormitrip.goormitrip.user.domain.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "refresh_token")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class RefreshToken extends BaseTimeEntity {

	@Id
	@Column(length = 256)
	private String token;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id",
		foreignKey = @ForeignKey(name = "fk_refresh_user"))
	private UserEntity user;

	@Column(name = "expiry_at", nullable = false)
	private LocalDateTime expiryAt;

	public static RefreshToken of(String token, UserEntity user, Duration ttl) {
		return RefreshToken.builder()
			.token(token)
			.user(user)
			.expiryAt(LocalDateTime.now().plus(ttl))
			.build();
	}

	public boolean isExpired() {
		return expiryAt.isBefore(LocalDateTime.now());
	}

	public void rotate(String newToken, Duration ttl) {
		this.token = newToken;
		this.expiryAt = LocalDateTime.now().plus(ttl);
	}
}
