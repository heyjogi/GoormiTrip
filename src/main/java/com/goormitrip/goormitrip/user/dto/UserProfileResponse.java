package com.goormitrip.goormitrip.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.domain.UserProfile;

public record UserProfileResponse(
	String nickname,
	String email,
	String phone,
	String address,
	@JsonProperty("profile_image")
	String profileImage
) {
	public static UserProfileResponse from(UserEntity user) {
		UserProfile p = user.getProfile();
		return new UserProfileResponse(
			p.getNickname(),
			user.getEmail(),
			p.getPhone(),
			p.getAddress(),
			p.getProfileImageUrl()
		);
	}
}