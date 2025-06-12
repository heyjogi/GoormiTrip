package com.goormitrip.goormitrip.product.service.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Sort;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.ProductStatus;
import com.goormitrip.goormitrip.product.exception.InvalidFilterParameterException;
import com.goormitrip.goormitrip.product.exception.InvalidSortParameterException;
import com.goormitrip.goormitrip.product.exception.KeywordRequiredException;
import com.goormitrip.goormitrip.product.exception.ProductComparisonMinimumException;
import com.goormitrip.goormitrip.product.exception.ProductNotFoundException;
import com.goormitrip.goormitrip.product.repository.ProductRepository;
import com.goormitrip.goormitrip.product.service.ProductService;

import jakarta.persistence.criteria.Predicate;

@Transactional(readOnly = true)
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	private static final Set<String> ALLOWED_SORT_KEYS = Set.of("latest", "price");
	private static final Set<String> ALLOWED_REGIONS = Set.of("jeju", "busan", "seoul");
	private static final Set<String> ALLOWED_THEMES = Set.of("healing", "food", "activity");

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> searchByKeyword(String keyword) {
		if (keyword == null || keyword.trim().isEmpty()) {
        throw new KeywordRequiredException();
    	}
		return productRepository.findByTitleContaining(keyword);
	}

	@Override
	public List<Product> getByStatus(ProductStatus status) {
		return productRepository.findByStatus(status);
	}

	@Override
	public List<Product> getByRegion(String region) {
		return productRepository.findByRegion(region);
	}

	@Override
	public List<Product> getByTheme(String theme) {
		return productRepository.findByTheme(theme);
	}

	@Override
	public List<Product> filterProducts(ProductStatus status, String region, String theme, String keyword, String sort) {

		System.out.println("=== Filtering Conditions ===");
		System.out.println("status = " + status);
		System.out.println("region = " + region);
		System.out.println("theme = " + theme);
		System.out.println("keyword = " + keyword);

		if (sort != null && !ALLOWED_SORT_KEYS.contains(sort)) {
			throw new InvalidSortParameterException();
		}

		Specification<Product> spec = (root, query, cb) -> {
			List<Predicate> predicates = new ArrayList<>();

			if (status != null) {
				predicates.add(cb.equal(root.get("status"), status));
			}
			if (region != null) {
				if (!ALLOWED_REGIONS.contains(region)) {
					throw new InvalidFilterParameterException();
				}
				predicates.add(cb.equal(root.get("region"), region));
			}
			if (theme != null) {
				if (!ALLOWED_THEMES.contains(theme)) {
					throw new InvalidFilterParameterException();
				}
				predicates.add(cb.equal(root.get("theme"), theme));
			}
			if (keyword != null && !keyword.isEmpty()) {
				Predicate titleMatch = cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
				Predicate descMatch = cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%");
				predicates.add(cb.or(titleMatch, descMatch));
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		Sort sortOption = Sort.unsorted();
		if ("latest".equals(sort)) {
			sortOption = Sort.by(Sort.Direction.DESC, "createdAt");
		} else if ("price".equals(sort)) {
			sortOption = Sort.by(Sort.Direction.ASC, "price");
		}

		return productRepository.findAll(spec, sortOption);
	}

	@Override
	public Product getProductById(Long id) {
		return productRepository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));
	}

	@Override
	@Transactional
	public Product updateProduct(Long id, Product updatedProduct) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));

		product.setTitle(updatedProduct.getTitle());
		product.setDescription(updatedProduct.getDescription());
		product.setPrice(updatedProduct.getPrice());
		product.setRegion(updatedProduct.getRegion());
		product.setTheme(updatedProduct.getTheme());
		product.setStatus(updatedProduct.getStatus());
		product.setStartDate(updatedProduct.getStartDate());
		product.setEndDate(updatedProduct.getEndDate());
		product.setUpdatedAt(LocalDateTime.now());
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public Product createProduct(Product product) {
		product.setCreatedAt(LocalDateTime.now());
		product.setUpdatedAt(LocalDateTime.now());
		return productRepository.save(product);
	}

	@Override
	@Transactional
	public void deleteProduct(Long id) {
		Product product = productRepository.findById(id)
			.orElseThrow(() -> new ProductNotFoundException(id));
		productRepository.delete(product);
	}

	@Override
	public List<Product> compareProducts(List<Long> ids) {
		if (ids == null || ids.size() < 2) {
        throw new ProductComparisonMinimumException();
    	}
		return productRepository.findAllById(ids);
	}
}

