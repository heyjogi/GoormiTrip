package com.goormitrip.goormitrip.oauth.service;

import java.time.Duration;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.oauth.domain.RefreshToken;
import com.goormitrip.goormitrip.oauth.repository.RefreshTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
	private static final Duration TTL = Duration.ofDays(14);

	private final RefreshTokenRepository repo;

	@Transactional
	public void store(Long userId, String newToken) {
		repo.deleteByUserId(userId);
		repo.save(RefreshToken.of(newToken, userId, TTL));
	}

	@Transactional
	public void revoke(String token) {
		repo.deleteById(token);
	}
}