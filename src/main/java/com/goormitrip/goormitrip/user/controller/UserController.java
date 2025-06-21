package com.goormitrip.goormitrip.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.user.service.UserQueryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

	private final UserQueryService userQueryService;

	@GetMapping("/me")
	public ResponseEntity<?> getMyProfile() {
		return ApiResponse.ok(userQueryService.getCurrentUserProfile());
	}
}