package com.goormitrip.goormitrip.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.exception.LoginFailedException;
import com.goormitrip.goormitrip.user.exception.SocialLoginUserCannotLoginException;
import com.goormitrip.goormitrip.user.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		final UserEntity user = userRepository.findByEmail(email)
			.orElseThrow(LoginFailedException::new);
		if (user.isSocialUser()) {
			throw new SocialLoginUserCannotLoginException();
		}

		return new CustomUserDetails(user);
	}
}
