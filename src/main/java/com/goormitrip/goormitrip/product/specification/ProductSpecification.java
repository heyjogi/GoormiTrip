package com.goormitrip.goormitrip.product.specification;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.ProductStatus;
import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
	public static Specification<Product> filter(ProductStatus status, String region, String theme, String keyword) {
		return (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (status != null) {
				predicates.add(cb.equal(root.get("status"), status));
			}
			if (region != null) {
				predicates.add(cb.equal(root.get("region"), region));
			}
			if (theme != null) {
				predicates.add(cb.equal(root.get("theme"), theme));
			}
			if (keyword != null && !keyword.trim().isEmpty()) {
				String likeKeyword = "%" + keyword.toLowerCase() + "%";
				Predicate titleMatch = cb.like(cb.lower(root.get("title")), likeKeyword);
				Predicate descMatch = cb.like(cb.lower(root.get("description")), likeKeyword);
				predicates.add(cb.or(titleMatch, descMatch));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};
	}
}
