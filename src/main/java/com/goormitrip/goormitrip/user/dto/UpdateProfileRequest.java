package com.goormitrip.goormitrip.user.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateProfileRequest(
	@NotBlank String nickname,
	String profileImageUrl,
	String phone,
	String address
) { }