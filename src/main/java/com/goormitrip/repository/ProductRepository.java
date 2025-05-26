package com.goormitrip.repository;

import com.goormitrip.domain.Product;
import com.goormitrip.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByTitleContaining(String keyword);
    List<Product> findByStatus(ProductStatus status);
    List<Product> findByRegion(String region);
    List<Product> findByTheme(String theme);
}
