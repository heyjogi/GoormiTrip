package com.goormitrip.goormitrip.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.oauth.domain.RefreshToken;

public interface RefreshTokenRepository
	extends JpaRepository<RefreshToken, String> {

	Optional<RefreshToken> findByUserId(Long userId);

	void deleteByUserId(Long userId);
}
