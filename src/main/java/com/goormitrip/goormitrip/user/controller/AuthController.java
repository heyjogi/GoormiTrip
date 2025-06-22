package com.goormitrip.goormitrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.user.dto.AuthRequest;
import com.goormitrip.goormitrip.user.dto.PasswordResetConfirmRequest;
import com.goormitrip.goormitrip.user.dto.PasswordResetRequest;
import com.goormitrip.goormitrip.user.dto.SignupRequest;
import com.goormitrip.goormitrip.user.service.AuthService;
import com.goormitrip.goormitrip.user.service.PasswordResetService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	private final PasswordResetService resetService;

	@PostMapping("/users/signup")
	public ResponseEntity<?> signup(@RequestBody @Valid SignupRequest request) {
		return ApiResponse.ok(authService.signup(request));
	}

	@PostMapping("/users/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request) {
		return ApiResponse.ok(authService.login(request));
	}

	@PostMapping("/auth/password-reset-request")
	public ResponseEntity<?> requestReset(@Valid @RequestBody PasswordResetRequest req) {
		resetService.requestReset(req.email());
		return ApiResponse.ok("비밀번호 재설정 링크가 이메일로 발송되었습니다.");
	}

	@PostMapping("/auth/password-reset")
	public ResponseEntity<?> resetPassword(
		@Valid @RequestBody PasswordResetConfirmRequest req) {

		resetService.resetPassword(req.reset_token(), req.new_password());
		return ApiResponse.ok("비밀번호가 성공적으로 변경되었습니다.");
	}
}
