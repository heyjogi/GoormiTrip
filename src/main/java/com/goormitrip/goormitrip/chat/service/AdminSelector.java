package com.goormitrip.goormitrip.chat.service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.stereotype.Component;

import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.domain.UserRole;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminSelector {

	private final UserRepository userRepo;

	public UserEntity pickAdmin() {
		final List<UserEntity> admins = userRepo.findAllByRole(UserRole.ADMIN);

		if (admins.isEmpty()) {
			throw new IllegalStateException("관리자가 존재하지 않습니다.");
		}
		int idx = ThreadLocalRandom.current().nextInt(admins.size());
		return admins.get(idx);
	}
}