package com.goormitrip.goormitrip.product.dto;

public record RecentProductResponse(
	Long productId,
	String productName,
	int price,
	String thumbnailUrl
) {
}
