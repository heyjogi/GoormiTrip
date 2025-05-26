package com.goormitrip.service.impl;

import com.goormitrip.domain.Product;
import com.goormitrip.domain.ProductStatus;
import com.goormitrip.repository.ProductRepository;
import com.goormitrip.service.ProductService;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.Predicate;

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
    public List<Product> filterProducts(ProductStatus status, String region, String theme, String keyword) {
    
        System.out.println("=== Filtering Conditions ===");
        System.out.println("status = " + status);
        System.out.println("region = " + region);
        System.out.println("theme = " + theme);
        System.out.println("keyword = " + keyword);

        Specification<Product> spec = (root, query, cb) -> {
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
            if (keyword != null && !keyword.isEmpty()) {
                Predicate titleMatch = cb.like(cb.lower(root.get("title")), "%" + keyword.toLowerCase() + "%");
                Predicate descMatch = cb.like(cb.lower(root.get("description")), "%" + keyword.toLowerCase() + "%");
                predicates.add(cb.or(titleMatch, descMatch));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

    return productRepository.findAll(spec);
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    @Override
    public Product updateProduct(Long id, Product updatedProduct) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));

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
    public Product createProduct(Product product) {
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }

    @Override
    public List<Product> compareProducts(List<Long> ids) {
        return productRepository.findAllById(ids);
    }
}

