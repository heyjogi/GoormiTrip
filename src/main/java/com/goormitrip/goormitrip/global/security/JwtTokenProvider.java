package com.goormitrip.goormitrip.global.security;

import com.goormitrip.goormitrip.user.domain.UserRole;

public interface JwtTokenProvider {
	String createAccessToken(Long userId, UserRole role);

	String createRefreshToken(Long userId);

	boolean isValid(String token);

	Long extractUserId(String token);
}
