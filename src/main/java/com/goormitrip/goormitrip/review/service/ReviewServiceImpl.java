package com.goormitrip.goormitrip.review.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.repository.ProductRepository;
import com.goormitrip.goormitrip.review.domain.Review;
import com.goormitrip.goormitrip.review.dto.CreateReviewRequest;
import com.goormitrip.goormitrip.review.dto.MyReviewResponse;
import com.goormitrip.goormitrip.review.dto.ReviewResponse;
import com.goormitrip.goormitrip.review.dto.UpdateReviewRequest;
import com.goormitrip.goormitrip.review.exception.ReviewNotFoundException;
import com.goormitrip.goormitrip.review.repository.ReviewRepository;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewServiceImpl implements ReviewService {

	private final ReviewRepository reviewRepo;
	private final ProductRepository productRepo;
	private final UserRepository userRepo;

	@Override
	@Transactional(readOnly = true)
	public Page<MyReviewResponse> getMyReviews(Long userId, Pageable pageable) {
		return reviewRepo.findByAuthorId(userId, pageable)
			.map(this::toMyReviewDto);
	}

	@Override
	public ReviewResponse createReview(Long userId, Long productId, CreateReviewRequest req) {
		UserEntity author = userRepo.getReferenceById(userId);
		Product product = productRepo.getReferenceById(productId);

		Review review = Review.create(author, product, req.rating(), req.content());
		reviewRepo.save(review);

		return toReviewDto(review);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<ReviewResponse> getProductReviews(Long productId, Pageable pageable) {
		return reviewRepo.findByProductId(productId, pageable)
			.map(this::toReviewDto);
	}

	@Override
	public ReviewResponse updateReview(Long userId, Long reviewId, UpdateReviewRequest req) {
		Review review = getOwnedReviewOrThrow(userId, reviewId);
		review.update(req.rating(), req.content());
		return toReviewDto(review);
	}

	@Override
	public void deleteReview(Long userId, Long reviewId) {
		Review review = getOwnedReviewOrThrow(userId, reviewId);
		reviewRepo.delete(review);
	}

	// ───────────────────────── private helpers ─────────────────────────
	private Review getOwnedReviewOrThrow(Long userId, Long reviewId) {
		Review review = reviewRepo.findById(reviewId)
			.orElseThrow(ReviewNotFoundException::new);
		if (!review.getAuthor().getId().equals(userId)) {
			throw new ReviewNotFoundException();
		}
		return review;
	}

	private ReviewResponse toReviewDto(Review r) {
		return ReviewResponse.builder()
			.id(r.getId())
			.authorId(r.getAuthor().getId())
			.productId(r.getProduct().getId())
			.rating(r.getRating())
			.content(r.getContent())
			.createdAt(r.getCreatedAt())
			.updatedAt(r.getUpdatedAt())
			.build();
	}

	private MyReviewResponse toMyReviewDto(Review r) {
		return MyReviewResponse.builder()
			.id(r.getId())
			.productId(r.getProduct().getId())
			.rating(r.getRating())
			.content(r.getContent())
			.createdAt(r.getCreatedAt())
			.updatedAt(r.getUpdatedAt())
			.build();
	}
}
