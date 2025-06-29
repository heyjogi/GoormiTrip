package com.goormitrip.goormitrip.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.goormitrip.goormitrip.review.dto.CreateReviewRequest;
import com.goormitrip.goormitrip.review.dto.MyReviewResponse;
import com.goormitrip.goormitrip.review.dto.ReviewResponse;
import com.goormitrip.goormitrip.review.dto.UpdateReviewRequest;

public interface ReviewService {

	Page<MyReviewResponse> getMyReviews(Long userId, Pageable pageable);

	ReviewResponse createReview(Long userId, Long productId, CreateReviewRequest req);

	Page<ReviewResponse> getProductReviews(Long productId, Pageable pageable);

	ReviewResponse updateReview(Long userId, Long reviewId, UpdateReviewRequest req);

	void deleteReview(Long userId, Long reviewId);
}

