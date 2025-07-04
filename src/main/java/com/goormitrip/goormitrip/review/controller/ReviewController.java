package com.goormitrip.goormitrip.review.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.security.CustomUserDetails;
import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.review.dto.CreateReviewRequest;
import com.goormitrip.goormitrip.review.dto.UpdateReviewRequest;
import com.goormitrip.goormitrip.review.service.ReviewService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ReviewController {

	private final ReviewService reviewService;

	@GetMapping("/mypage/reviews")
	public ResponseEntity<?> getMyReviews(
		@AuthenticationPrincipal CustomUserDetails user,
		@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		return ApiResponse.ok(reviewService.getMyReviews(user.getId(), pageable));
	}

	@PostMapping("/products/{productId}/reviews")
	public ResponseEntity<?> createReview(
		@AuthenticationPrincipal CustomUserDetails user,
		@PathVariable Long productId,
		@Valid @RequestBody CreateReviewRequest req
	) {
		return ApiResponse.ok(reviewService.createReview(user.getId(), productId, req));
	}

	@GetMapping("/products/{productId}/reviews")
	public ResponseEntity<?> getProductReviews(
		@PathVariable Long productId,
		@PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
	) {
		return ApiResponse.ok(reviewService.getProductReviews(productId, pageable));
	}

	@PutMapping("/reviews/{reviewId}")
	public ResponseEntity<?> updateReview(
		@AuthenticationPrincipal CustomUserDetails user,
		@PathVariable Long reviewId,
		@Valid @RequestBody UpdateReviewRequest req) {
		return ApiResponse.ok(reviewService.updateReview(user.getId(), reviewId, req));
	}

	@DeleteMapping("/reviews/{reviewId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteReview(
		@AuthenticationPrincipal CustomUserDetails user,
		@PathVariable Long reviewId
	) {
		reviewService.deleteReview(user.getId(), reviewId);
	}
}
