package com.goormitrip.goormitrip.oauth.service;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.oauth.domain.SocialProvider;
import com.goormitrip.goormitrip.oauth.dto.OAuthAttributes;
import com.goormitrip.goormitrip.oauth.security.CustomOAuth2User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService
	implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {

	private final SocialUserFacade userFacade;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest req) throws OAuth2AuthenticationException {
		DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
		OAuth2User oauth2User = delegate.loadUser(req);

		SocialProvider provider =
			SocialProvider.valueOf(req.getClientRegistration().getRegistrationId().toUpperCase());
		OAuthAttributes attrs = switch (provider) {
			case GOOGLE -> OAuthAttributes.ofGoogle(oauth2User.getAttributes());
			case KAKAO -> OAuthAttributes.ofKakao(oauth2User.getAttributes());
			case NAVER -> OAuthAttributes.ofNaver(oauth2User.getAttributes());
		};

		CustomUserDetails principal =
			new CustomUserDetails(userFacade.findOrCreate(attrs));

		final String nameAttributeKey = req.getClientRegistration()
			.getProviderDetails()
			.getUserInfoEndpoint()
			.getUserNameAttributeName();

		return new CustomOAuth2User(
			principal,
			principal.getAuthorities(),
			oauth2User.getAttributes(),
			nameAttributeKey
		);
	}
}