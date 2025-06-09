package com.goormitrip.goormitrip.oauth;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.user.domain.UserEntity;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private static final Duration TTL = Duration.ofDays(14);

	private final RefreshTokenRepository repo;

	@Transactional
	public void store(UserEntity user, String newToken) {
		repo.deleteByUser(user);
		repo.save(RefreshToken.of(newToken, user, TTL));
	}

	@Transactional
	public void revoke(String token) {
		repo.deleteById(token);
	}
}