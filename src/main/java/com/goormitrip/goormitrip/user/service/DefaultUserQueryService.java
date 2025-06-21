package com.goormitrip.goormitrip.user.service;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.user.dto.UserProfileResponse;
import com.goormitrip.goormitrip.user.exception.UserNotFoundException;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DefaultUserQueryService implements UserQueryService {

	private final UserRepository userRepository;

	@Override
	public UserProfileResponse getCurrentUserProfile() {
		final Long userId = (Long) SecurityContextHolder.getContext()
			.getAuthentication()
			.getPrincipal();

		return userRepository.findById(userId)
			.map(UserProfileResponse::from)
			.orElseThrow(UserNotFoundException::new);
	}
}
