package com.goormitrip.goormitrip.cart.repository;

import com.goormitrip.goormitrip.cart.domain.Cart;
import com.goormitrip.goormitrip.cart.domain.CartItem;
import com.goormitrip.goormitrip.product.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByCart(Cart cart);

    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}
