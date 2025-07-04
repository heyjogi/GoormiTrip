package com.goormitrip.goormitrip.wishlist.service;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.wishlist.dto.WishlistResponse;

import java.util.List;

public interface WishlistService {
    void addToWishlist(UserEntity user, Product product);

    void removeFromWishlist(UserEntity user, Product product);

    List<WishlistResponse> getWishlist(UserEntity user);
}
