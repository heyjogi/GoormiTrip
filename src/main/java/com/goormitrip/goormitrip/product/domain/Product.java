package com.goormitrip.goormitrip.product.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;

    @Column
    private String description;

    private BigDecimal price;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String region;

    private String theme;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    private int minPeople;
    private int maxPeople;

    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;
}
