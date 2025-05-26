package com.goormitrip.goormitrip.product.service;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.ProductStatus;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();

    List<Product> searchByKeyword(String keyword);

    List<Product> getByStatus(ProductStatus status);

    List<Product> getByRegion(String region);

    List<Product> getByTheme(String theme);

    List<Product> filterProducts(ProductStatus status, String region, String theme, String keyword);

    Product getProductById(Long id);

    Product updateProduct(Long id, Product updatedProduct);

    Product createProduct(Product product);

    void deleteProduct(Long id);

    List<Product> compareProducts(List<Long> ids);

}
