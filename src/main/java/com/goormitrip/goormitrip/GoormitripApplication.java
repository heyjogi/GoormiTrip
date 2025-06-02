package com.goormitrip.goormitrip;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.product.domain.ProductStatus;
import com.goormitrip.goormitrip.product.repository.ProductRepository;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableJpaAuditing
public class GoormitripApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoormitripApplication.class, args);
	}

	@Bean
	public CommandLineRunner dummyData(ProductRepository productRepository) {
		return args -> {
			Product product = new Product();
			product.setTitle("제주도 여행 상품");
			product.setDescription("푸른 바다와 함께하는 3박 4일 힐링 여행");
			product.setPrice(new BigDecimal("299000"));
			product.setRegion("Jeju");
			product.setTheme("Nature");
			product.setStatus(ProductStatus.ACTIVE);
			product.setStartDate(LocalDateTime.now().plusDays(7));
			product.setEndDate(LocalDateTime.now().plusDays(10));
			product.setCreatedAt(LocalDateTime.now());
			product.setUpdatedAt(LocalDateTime.now());

			productRepository.save(product);
			System.out.println("Dummy product inserted");
		};
	}

	;
}

