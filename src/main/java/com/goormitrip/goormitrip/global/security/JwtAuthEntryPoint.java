package com.goormitrip.goormitrip.global.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(HttpServletRequest req, HttpServletResponse res, AuthenticationException ex
	) throws IOException {
		final ApiResponse<?> body = ApiResponse.error("인증이 필요합니다.", HttpStatus.UNAUTHORIZED).getBody();
		res.setStatus(HttpStatus.UNAUTHORIZED.value());
		res.setContentType(MediaType.APPLICATION_JSON_VALUE + ";charset=UTF-8");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(new ObjectMapper().writeValueAsString(body));
	}
}
