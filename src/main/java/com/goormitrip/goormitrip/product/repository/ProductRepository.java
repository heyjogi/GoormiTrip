package com.goormitrip.goormitrip.product.repository;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.ProductStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findByTitleContaining(String keyword);

    List<Product> findByStatus(ProductStatus status);

    List<Product> findByRegion(String region);

    List<Product> findByTheme(String theme);
}
