package com.goormitrip.goormitrip.product.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.ProductStatus;
import com.goormitrip.goormitrip.product.dto.CompareRequest;
import com.goormitrip.goormitrip.product.service.ProductService;

@RestController
@RequestMapping("/api/products")

public class ProductController {

	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public ResponseEntity<?> getAllProducts() {
		final List<Product> allProducts = productService.getAllProducts();
		return ApiResponse.ok(allProducts);
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
			@RequestParam(required = false) String keyword,
			@RequestParam(required = false) String sort) {
		return productService.filterProducts(status, region, theme, keyword, sort);
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
	public ResponseEntity<String> deleteProduct(@PathVariable("id") Long id) {
		productService.deleteProduct(id);
		return ResponseEntity.ok("Product deleted successfully");
	}

	@PostMapping("/compare")
	public List<Product> compareProducts(@RequestBody CompareRequest request) {
		return productService.compareProducts(request.getIds());
	}

}
