package com.goormitrip.goormitrip.wishlist.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder

public class WishlistResponse {
    private Long id;
    private Long productId;
    private String productTitle;
    private String thumbnail;
}
