package com.goormitrip.goormitrip.review.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.goormitrip.goormitrip.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	Page<Review> findByAuthorId(Long authorId, Pageable pageable);

	Page<Review> findByProductId(Long productId, Pageable pageable);

	boolean existsByIdAndAuthorId(Long reviewId, Long authorId);
}

