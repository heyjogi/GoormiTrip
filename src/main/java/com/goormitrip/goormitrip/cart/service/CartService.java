package com.goormitrip.goormitrip.cart.service;

import com.goormitrip.goormitrip.cart.domain.CartItem;
import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.cart.dto.CartItemResponse;

import java.util.List;

public interface CartService {
    void addToCart(UserEntity user, Product product, int peopleCount, String travelDate);

    void removeFromCartItem(Long itemId, UserEntity user);

    List<CartItem> getCartItems(UserEntity user);

    List<CartItemResponse> getCartItemResponses(UserEntity user);
}
