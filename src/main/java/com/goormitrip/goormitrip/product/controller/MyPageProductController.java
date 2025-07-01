package com.goormitrip.goormitrip.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.product.dto.RecentProductResponse;
import com.goormitrip.goormitrip.product.service.RecentProductService;
import com.goormitrip.goormitrip.user.domain.UserEntity;

@RestController
@RequestMapping("/mypage")
public class MyPageProductController {

	private final RecentProductService recentProductService;

	public MyPageProductController(RecentProductService recentProductService) {
		this.recentProductService = recentProductService;
	}

	@GetMapping("/recent-products")
	public ResponseEntity<ApiResponse<List<RecentProductResponse>>> getRecentProducts(
		@AuthenticationPrincipal UserEntity user
	) {
		
		List<RecentProductResponse> result = recentProductService.getRecentViewedProducts(user);

		return ApiResponse.ok(result);
	}
}
