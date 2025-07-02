package com.goormitrip.goormitrip.product.service.impl;

import java.util.*;

import org.springframework.stereotype.Service;

import com.goormitrip.goormitrip.product.domain.RecentProduct;
import com.goormitrip.goormitrip.product.dto.RecentProductResponse;
import com.goormitrip.goormitrip.product.repository.RecentProductRepository;
import com.goormitrip.goormitrip.product.service.RecentProductService;

@Service
public class RecentProductServiceImpl implements RecentProductService {

	private final RecentProductRepository recentProductRepository;

	public RecentProductServiceImpl(RecentProductRepository recentProductRepository) {
		this.recentProductRepository = recentProductRepository;
	}

	@Override
	public List<RecentProductResponse> getRecentViewedProducts(Long userId) {

		List<RecentProduct> recentProducts = recentProductRepository
			.findByUserIdWithProductOrderByViewedAtDesc(userId);

		return recentProducts.stream()
			.map(rp -> new RecentProductResponse(
				rp.getProduct().getId(),
				rp.getProduct().getTitle(),
				rp.getProduct().getPrice().intValue(),
				rp.getProduct().getThumbnail()
			))
			.toList();
	}
}
