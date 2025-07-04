package com.goormitrip.goormitrip.wishlist.controller;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.wishlist.domain.Wishlist;
import com.goormitrip.goormitrip.wishlist.dto.WishlistResponse;
import com.goormitrip.goormitrip.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> addToWishlist(
            @RequestParam Long productId,
            @AuthenticationPrincipal UserEntity user) {
        Product product = Product.builder().id(productId).build();
        wishlistService.addToWishlist(user, product);

        return ResponseEntity.ok("찜한 상품에 추가되었습니다.");
    }

    @DeleteMapping
    @PreAuthorize("isAuthenticated()")
    public void removeFromWishlist(
            @RequestParam Long productId,
            @AuthenticationPrincipal UserEntity user) {
        Product product = Product.builder().id(productId).build();
        wishlistService.removeFromWishlist(user, product);
    }

}
