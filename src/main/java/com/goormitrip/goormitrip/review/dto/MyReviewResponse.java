package com.goormitrip.goormitrip.review.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record MyReviewResponse(
	Long id,
	Long productId,
	int rating,
	String content,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {}
