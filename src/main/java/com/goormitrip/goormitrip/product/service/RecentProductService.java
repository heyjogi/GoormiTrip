package com.goormitrip.goormitrip.product.service;

import java.util.List;

import com.goormitrip.goormitrip.product.dto.RecentProductResponse;

public interface RecentProductService {
	List<RecentProductResponse> getRecentViewedProducts(Long userId);
}
