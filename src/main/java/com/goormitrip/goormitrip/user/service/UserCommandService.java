package com.goormitrip.goormitrip.user.service;

import com.goormitrip.goormitrip.user.dto.ChangePasswordRequest;
import com.goormitrip.goormitrip.user.dto.UpdateProfileRequest;

public interface UserCommandService {
	void updateProfile(Long userId, UpdateProfileRequest dto);

	void deleteAccount(Long userId);

	void updatePassword(Long userId, ChangePasswordRequest dto);
}
