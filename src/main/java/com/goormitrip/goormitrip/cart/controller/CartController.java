package com.goormitrip.goormitrip.cart.controller;

import com.goormitrip.goormitrip.cart.dto.CartItemResponse;
import com.goormitrip.goormitrip.cart.service.CartService;
import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<?> addToCart(
            @RequestParam Long productId,
            @RequestParam int peopleCount,
            @RequestParam String travelDate,
            @AuthenticationPrincipal UserEntity user) {
        Product product = Product.builder().id(productId).build();
        cartService.addToCart(user, product, peopleCount, travelDate);
        return ResponseEntity.ok("장바구니에 추가됨");
    }

    @DeleteMapping("/item/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.removeFromCartItem(id);
    }

    @GetMapping
    public List<CartItemResponse> getCartItems(@RequestAttribute("user") UserEntity user) {
        return cartService.getCartItemResponses(user);
    }
}
