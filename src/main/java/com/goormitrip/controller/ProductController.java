package com.goormitrip.controller;

import com.goormitrip.domain.Product;
import com.goormitrip.domain.ProductStatus;
import com.goormitrip.dto.CompareRequest;
import com.goormitrip.service.ProductService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/products")

public class ProductController {
    
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/search")
    public List<Product> searchProducts(@RequestParam String keyword) {
        return productService.searchByKeyword(keyword);
    }

     @GetMapping("/status/{status}")
    public List<Product> getByStatus(@PathVariable ProductStatus status) {
        return productService.getByStatus(status);
    }

    @GetMapping("/region/{region}")
    public List<Product> getByRegion(@PathVariable String region) {
        return productService.getByRegion(region);
    }

    @GetMapping("/theme/{theme}")
    public List<Product> getByTheme(@PathVariable String theme) {
        return productService.getByTheme(theme);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/filter/search")
    public List<Product> filterProducts(
        @RequestParam(required = false) ProductStatus status,
        @RequestParam(required = false) String region,
        @RequestParam(required = false) String theme,
        @RequestParam(required = false) String keyword
    ) {
        return productService.filterProducts(status, region, theme, keyword);
    }
    
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productService.updateProduct(id, updatedProduct);
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.ok("Product deleted successfully");
    }

    @PostMapping("/compare")
    public List<Product> compareProducts(@RequestBody CompareRequest request) {
        return productService.compareProducts(request.getIds());
    }

}
