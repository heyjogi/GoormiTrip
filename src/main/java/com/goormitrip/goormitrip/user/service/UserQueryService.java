package com.goormitrip.goormitrip.user.service;

import com.goormitrip.goormitrip.user.dto.UserProfileResponse;

public interface UserQueryService {
	UserProfileResponse getCurrentUserProfile();
}