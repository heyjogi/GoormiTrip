package com.goormitrip.goormitrip.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.dto.UpdateProfileRequest;
import com.goormitrip.goormitrip.user.exception.UserNotFoundException;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
class DefaultUserCommandService implements UserCommandService {

	private final UserRepository userRepo;

	@Override
	public void updateProfile(Long userId, UpdateProfileRequest dto) {
		final UserEntity user = userRepo.findFetchProfileById(userId)
			.orElseThrow(UserNotFoundException::new);

		user.getProfile()
			.updateProfile(
				dto.nickname(),
				dto.profileImageUrl(),
				dto.phone(),
				dto.address()
			);
	}
}