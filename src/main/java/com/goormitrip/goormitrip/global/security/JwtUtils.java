package com.goormitrip.goormitrip.global.security;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtUtils implements JwtTokenProvider {

	private static final long ACCESS_TOKEN_EXPIRATION = 60L * 30; // 30분
	private static final long REFRESH_TOKEN_EXPIRATION = 60L * 60 * 24 * 14; // 14일
	private SecretKey key;
	@Value("${JWT_SECRET_KEY}")
	private String jwtSecret;

	@PostConstruct
	void initKey() {
		key = Keys.hmacShaKeyFor(jwtSecret.getBytes());
	}

	@Override
	public String createAccessToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		CustomUserDetails customUserDetails = (CustomUserDetails) userDetails;
		claims.put("role", customUserDetails.getRole().name());
		return createToken(claims, customUserDetails.getUsername(), ACCESS_TOKEN_EXPIRATION);
	}

	@Override
	public String createRefreshToken(UserDetails userDetails) {
		return createToken(new HashMap<>(), userDetails.getUsername(), REFRESH_TOKEN_EXPIRATION);
	}

	private String createToken(Map<String, Object> claims, String subject, long expirySeconds) {
		Instant now = Instant.now();
		return Jwts.builder()
				.setClaims(claims)
				.setSubject(subject)
				.setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plusSeconds(expirySeconds)))
				.signWith(key, SignatureAlgorithm.HS512)
				.compact();
	}

	private boolean isValid(String token) {
		try {
			Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
			return true;
		} catch (JwtException | IllegalArgumentException e) {
			log.warn("[JWT] Invalid token: {}", e.getMessage());
			return false;
		}
	}

	@Override
	public boolean isTokenValid(String token, UserDetails userDetails) {
		if (!isValid(token))
			return false;
		final String username = extractUsername(token);
		return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
	}

	private boolean isTokenExpired(String token) {
		try {
			return extractExpiration(token).before(new Date());
		} catch (ExpiredJwtException e) {
			return true;
		}
	}

	@Override
	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	private Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		try {
			return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
		} catch (ExpiredJwtException e) {
			return e.getClaims();
		}
	}
}
