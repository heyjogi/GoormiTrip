package com.goormitrip.goormitrip.product.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.ProductStatus;
import com.goormitrip.goormitrip.product.exception.ProductComparisonMinimumException;
import com.goormitrip.goormitrip.product.exception.ProductNotFoundException;
import com.goormitrip.goormitrip.product.exception.ValidationUtils;
import com.goormitrip.goormitrip.product.repository.ProductRepository;
import com.goormitrip.goormitrip.product.service.ProductService;
import com.goormitrip.goormitrip.product.specification.ProductSpecification;

@Transactional(readOnly = true)
@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> searchByKeyword(String keyword) {
		ValidationUtils.validateKeyword(keyword);
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
	public List<Product> filterProducts(ProductStatus status, String region, String theme, String keyword,
			String sort) {

		ValidationUtils.validateRegion(region);
		ValidationUtils.validateTheme(theme);
		ValidationUtils.validateSort(sort);

		Specification<Product> spec = ProductSpecification.filter(status, region, theme, keyword);

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
