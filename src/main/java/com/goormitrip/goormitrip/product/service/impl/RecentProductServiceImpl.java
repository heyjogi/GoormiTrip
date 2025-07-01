package com.goormitrip.goormitrip.product.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.dto.RecentProductResponse;
import com.goormitrip.goormitrip.product.repository.ProductRepository;
import com.goormitrip.goormitrip.product.repository.RecentProductRepository;
import com.goormitrip.goormitrip.product.service.RecentProductService;
import com.goormitrip.goormitrip.user.domain.UserEntity;

@Service
public class RecentProductServiceImpl implements RecentProductService {

	private final ProductRepository productRepository;
	private final RecentProductRepository recentProductRepository;

	public RecentProductServiceImpl(ProductRepository productRepository,
		RecentProductRepository recentProductRepository) {
		this.productRepository = productRepository;
		this.recentProductRepository = recentProductRepository;
	}

	@Override
	public List<RecentProductResponse> getRecentViewedProducts(UserEntity user) {
		List<Long> recentProductIds = recentProductRepository.findRecentProductIdsByUser(user);

		List<Product> products = productRepository.findByProductId(recentProductIds);

		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, p -> p));

		return recentProductIds.stream()
			.map(productMap::get)
			.filter(Objects::nonNull)
			.map(p -> new RecentProductResponse(
				p.getId(),
				p.getTitle(),
				p.getPrice().intValue(),
				p.getThumbnail()
			))
			.toList();
	}

}
