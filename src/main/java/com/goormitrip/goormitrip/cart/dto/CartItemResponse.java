package com.goormitrip.goormitrip.cart.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder

public class CartItemResponse {
    private Long cartItemId;

    private Long productId;
    private String productTitle;
    private String productThumbnail;
    private BigDecimal price;

    private int peopleCount;
    private String travelDate;
}
