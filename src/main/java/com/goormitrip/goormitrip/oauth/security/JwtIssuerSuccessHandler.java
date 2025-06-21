package com.goormitrip.goormitrip.oauth.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.security.JwtTokenProvider;
import com.goormitrip.goormitrip.global.security.TokenResponse;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.oauth.service.RefreshTokenService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtIssuerSuccessHandler implements AuthenticationSuccessHandler {

	private final JwtTokenProvider tokenProvider;
	private final RefreshTokenService refreshService;
	private final ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(
		HttpServletRequest req,
		HttpServletResponse res,
		Authentication auth
	) throws IOException {

		final CustomOAuth2User oAuth2User = (CustomOAuth2User) auth.getPrincipal();
		final CustomUserDetails userDetails = oAuth2User.getUserDetails();

		String access = tokenProvider.createAccessToken(userDetails);
		String refresh = tokenProvider.createRefreshToken(userDetails);
		refreshService.store(userDetails.getId(), refresh);

		TokenResponse tokenResponse = new TokenResponse(access, refresh);
		ApiResponse<?> apiResponse = createSuccessResponse(tokenResponse);

		res.setContentType("application/json");
		res.setCharacterEncoding("UTF-8");
		res.getWriter().write(objectMapper.writeValueAsString(apiResponse));
	}

	private <T> ApiResponse<T> createSuccessResponse(T data) {
		return ApiResponse.ok(data).getBody();
	}
}