package com.goormitrip.goormitrip.global.security;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenProvider {

	String createAccessToken(UserDetails userDetails);

	String createRefreshToken(UserDetails userDetails);

	boolean isTokenValid(String token, UserDetails userDetails);

	String extractUsername(String token);
}
