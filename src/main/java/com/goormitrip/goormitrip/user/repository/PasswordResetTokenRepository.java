package com.goormitrip.goormitrip.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.user.domain.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long> {
	Optional<PasswordResetToken> findByToken(String token);
}
