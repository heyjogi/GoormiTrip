package com.goormitrip.goormitrip.user.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.security.JwtUtils;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.domain.UserRole;
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

	private final JwtUtils jwtUtils;

	@Transactional
	public AuthResponse signup(SignupRequest request) {
		if (userRepository.existsByEmail(request.getEmail())) {
			throw new EmailDuplicateException();
		}

		UserEntity user = new UserEntity();
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		user.setPhone(request.getPhone());
		user.setRole(UserRole.USER);

		userRepository.save(user);

		// 회원가입 후 인증 처리 없이도 JWT 생성 가능
		// Authentication authentication = authenticationManager.authenticate(
		//     new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		// );

		// SecurityContextHolder.getContext().setAuthentication(authentication);

		CustomUserDetails userDetails = new CustomUserDetails(user);
		String jwt = jwtUtils.generateToken(userDetails);

		return new AuthResponse(jwt, user.getEmail(), user.getRole().name());
	}

	public AuthResponse login(AuthRequest request) {
		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
		);

		CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
		UserEntity user = userDetails.getUser();

		String jwt = jwtUtils.generateToken(userDetails);
		// SecurityContextHolder.getContext().setAuthentication(authentication);

		return new AuthResponse(jwt, user.getEmail(), user.getRole().name());
	}

}
