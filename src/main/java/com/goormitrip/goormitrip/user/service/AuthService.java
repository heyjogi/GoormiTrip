package com.goormitrip.goormitrip.user.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.security.JwtTokenProvider;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.dto.AuthRequest;
import com.goormitrip.goormitrip.user.dto.AuthResponse;
import com.goormitrip.goormitrip.user.dto.SignupRequest;
import com.goormitrip.goormitrip.user.exception.EmailDuplicateException;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

	private final AuthenticationManager authenticationManager;

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	private final JwtTokenProvider jwtTokenProvider;

	@Transactional
	public AuthResponse signup(SignupRequest req) {
		ensureEmailNotDuplicated(req.getEmail());

		UserEntity user = UserEntity.createLocal(req, passwordEncoder);
		userRepository.save(user);

		String jwt = jwtTokenProvider.createAccessToken(new CustomUserDetails(user));
		return new AuthResponse(jwt, user.getEmail(), user.getRole().name());
	}

	private void ensureEmailNotDuplicated(String email) {
		if (userRepository.existsByEmail(email)) {
			throw new EmailDuplicateException();
		}
	}

	public AuthResponse login(AuthRequest request) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);

		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();

		String jwt = jwtTokenProvider.createAccessToken(userDetails);

		return new AuthResponse(jwt, userDetails.getEmail(), userDetails.getRole().name());
	}
}
