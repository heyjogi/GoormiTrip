package com.goormitrip.goormitrip.review.dto;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record ReviewResponse(
	Long id,
	Long authorId,
	Long productId,
	int rating,
	String content,
	LocalDateTime createdAt,
	LocalDateTime updatedAt
) {}