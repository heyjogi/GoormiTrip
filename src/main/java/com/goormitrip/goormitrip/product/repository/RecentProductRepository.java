package com.goormitrip.goormitrip.product.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.goormitrip.goormitrip.product.domain.RecentProduct;

public interface RecentProductRepository
	extends JpaRepository<RecentProduct, Long> {

	@Query("SELECT rp FROM RecentProduct rp " +
		"JOIN FETCH rp.product " +
		"WHERE rp.user.id = :userId " +
		"ORDER BY rp.viewedAt DESC")
	List<RecentProduct> findByUserIdWithProductOrderByViewedAtDesc(@Param("userId") Long userId);

}
