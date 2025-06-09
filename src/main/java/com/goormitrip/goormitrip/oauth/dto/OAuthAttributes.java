package com.goormitrip.goormitrip.oauth.dto;

import java.util.Map;

import com.goormitrip.goormitrip.oauth.domain.SocialProvider;

public record OAuthAttributes(
	SocialProvider provider,
	String providerUserId,
	String email,
	String nickname
) {

	public static OAuthAttributes ofGoogle(Map<String, Object> attr) {
		return new OAuthAttributes(
			SocialProvider.GOOGLE,
			(String)attr.get("sub"),
			(String)attr.get("email"),
			(String)attr.get("name"));
	}

	public static OAuthAttributes ofKakao(Map<String, Object> attr) {
		Map<String, Object> kakaoAccount = (Map<String, Object>)attr.get("kakao_account");
		Map<String, Object> profile = (Map<String, Object>)kakaoAccount.get("profile");
		return new OAuthAttributes(
			SocialProvider.KAKAO,
			String.valueOf(attr.get("id")),
			(String)kakaoAccount.get("email"),
			(String)profile.get("nickname"));
	}

	public static OAuthAttributes ofNaver(Map<String, Object> attr) {
		Map<String, Object> response = (Map<String, Object>)attr.get("response");
		return new OAuthAttributes(
			SocialProvider.NAVER,
			(String)response.get("id"),
			(String)response.get("email"),
			(String)response.get("name"));
	}
}