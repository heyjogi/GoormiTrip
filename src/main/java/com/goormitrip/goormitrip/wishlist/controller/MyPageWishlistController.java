package com.goormitrip.goormitrip.wishlist.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.wishlist.dto.WishlistResponse;
import com.goormitrip.goormitrip.wishlist.service.WishlistService;

@RestController
@RequestMapping("/mypage")
public class MyPageWishlistController {

	private final WishlistService wishlistService;

	public MyPageWishlistController(WishlistService wishlistService) {
		this.wishlistService = wishlistService;
	}

	@GetMapping("/wishlist")
	public ResponseEntity<ApiResponse<List<WishlistResponse>>> getMyWishlist(
		@AuthenticationPrincipal UserEntity user) {
		List<WishlistResponse> response = wishlistService.getWishlist(user);
		return ApiResponse.ok(response);

	}
}
