package com.goormitrip.goormitrip.global.security;

import java.security.Key;
import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.goormitrip.goormitrip.user.domain.UserRole;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtTokenProviderImpl implements JwtTokenProvider {

	@Value("${JWT_SECRET_KEY}")
	private String secret;
	private Key key;

	@PostConstruct
	void initKey() {
		key = Keys.hmacShaKeyFor(secret.getBytes());
	}

	public String createAccessToken(Long userId, UserRole role) {
		Instant now = Instant.now();
		return Jwts.builder()
			.setSubject(String.valueOf(userId))
			.claim("role", role)
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(now.plusSeconds(900))) // 15 minutes
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public String createRefreshToken(Long userId) {
		Instant now = Instant.now();
		return Jwts.builder()
			.setSubject(String.valueOf(userId))
			.setIssuedAt(Date.from(now))
			.setExpiration(Date.from(now.plusSeconds(1209600))) // 14 days
			.signWith(key, SignatureAlgorithm.HS256)
			.compact();
	}

	public boolean isValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			return false;
		}
	}

	public Long extractUserId(String token) {
		return Long.valueOf(Jwts.parserBuilder()
			.setSigningKey(key)
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject());
	}
}
