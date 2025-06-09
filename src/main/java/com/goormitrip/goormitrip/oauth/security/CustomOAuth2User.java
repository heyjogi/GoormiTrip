package com.goormitrip.goormitrip.oauth.security;

import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;

public class CustomOAuth2User extends DefaultOAuth2User {

	private final CustomUserDetails customUserDetails;

	public CustomOAuth2User(CustomUserDetails userDetails,
		Collection<? extends GrantedAuthority> authorities,
		Map<String, Object> attributes,
		String nameAttributeKey) {
		super(authorities, attributes, nameAttributeKey);
		this.customUserDetails = userDetails;
	}

	public CustomUserDetails getUserDetails() {
		return customUserDetails;
	}
}