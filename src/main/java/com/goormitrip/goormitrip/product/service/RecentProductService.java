package com.goormitrip.goormitrip.product.service;

import java.util.List;

import com.goormitrip.goormitrip.product.dto.RecentProductResponse;
import com.goormitrip.goormitrip.user.domain.UserEntity;

public interface RecentProductService {
	List<RecentProductResponse> getRecentViewedProducts(UserEntity user);
}
