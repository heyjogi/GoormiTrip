package com.goormitrip.goormitrip.oauth.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.oauth.domain.RefreshToken;
import com.goormitrip.goormitrip.user.domain.UserEntity;

public interface RefreshTokenRepository
	extends JpaRepository<RefreshToken, String> {

	Optional<RefreshToken> findByUser(UserEntity user);

	void deleteByUser(UserEntity user);
}
