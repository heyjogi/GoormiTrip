package com.goormitrip.goormitrip.product.repository;

import java.util.List;

public interface RecentProductRepository {
	List<Long> findRecentProductIdsByUser(Long userId);
}
