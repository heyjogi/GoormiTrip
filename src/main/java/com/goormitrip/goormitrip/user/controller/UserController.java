package com.goormitrip.goormitrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.user.dto.UpdateProfileRequest;
import com.goormitrip.goormitrip.user.service.UserCommandService;
import com.goormitrip.goormitrip.user.service.UserQueryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
@RequiredArgsConstructor
public class UserController {

	private final UserQueryService userQueryService;
	private final UserCommandService userCommandService;

	@GetMapping("/me")
	public ResponseEntity<?> getMyProfile(@AuthenticationPrincipal CustomUserDetails user) {
		return ApiResponse.ok(userQueryService.getCurrentUserProfile(user.getId()));
	}

	@PutMapping("/me")
	public ResponseEntity<?> updateMyProfile(
		@AuthenticationPrincipal CustomUserDetails user,
		@Valid @RequestBody UpdateProfileRequest req) {

		userCommandService.updateProfile(user.getId(), req);
		final var updatedProfileResponse = userQueryService.getCurrentUserProfile(user.getId());
		return ApiResponse.ok(updatedProfileResponse);
	}
}