package com.goormitrip.goormitrip.review.domain;

import com.goormitrip.goormitrip.global.util.BaseTimeEntity;
import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "reviews")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private UserEntity author;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	private Product product;

	@Column(nullable = false)
	private int rating;

	@Column(nullable = false, length = 1000)
	private String content;

	private Review(UserEntity author, Product product, int rating, String content) {
		this.author = author;
		this.product = product;
		this.rating = rating;
		this.content = content;
	}

	public static Review create(UserEntity author, Product product, int rating, String content) {
		return new Review(author, product, rating, content);
	}

	public void update(int rating, String content) {
		this.rating = rating;
		this.content = content;
	}
}
