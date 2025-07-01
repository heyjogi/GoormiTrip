package com.goormitrip.goormitrip.product.repository;

import java.util.List;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;

public interface RecentProductRepository {
	List<Long> findRecentProductIdsByUser(UserEntity user);
}
