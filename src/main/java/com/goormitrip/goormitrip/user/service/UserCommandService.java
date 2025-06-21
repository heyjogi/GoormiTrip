package com.goormitrip.goormitrip.user.service;

import com.goormitrip.goormitrip.user.dto.UpdateProfileRequest;

public interface UserCommandService {
	void updateProfile(Long userId, UpdateProfileRequest dto);
}
