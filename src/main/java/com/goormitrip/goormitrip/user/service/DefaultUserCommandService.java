package com.goormitrip.goormitrip.user.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.dto.ChangePasswordRequest;
import com.goormitrip.goormitrip.user.dto.UpdateProfileRequest;
import com.goormitrip.goormitrip.user.exception.UserNotFoundException;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
class DefaultUserCommandService implements UserCommandService {

	private final UserRepository userRepo;
	private final PasswordEncoder passwordEncoder;

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

	@Override
	public void deleteAccount(Long userId) {
		UserEntity user = userRepo.findById(userId)
			.orElseThrow(UserNotFoundException::new);

		userRepo.delete(user);
	}

	@Override
	public void updatePassword(Long userId, ChangePasswordRequest dto) {
		UserEntity user = userRepo.findById(userId)
			.orElseThrow(UserNotFoundException::new);

		if (!passwordEncoder.matches(dto.currentPassword(), user.getPassword())) {
			throw new IllegalArgumentException("현재 비밀번호가 일치하지 않습니다.");
		}

		user.setPassword(passwordEncoder.encode(dto.newPassword()));
	}
}