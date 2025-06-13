package com.goormitrip.goormitrip.wishlist.controller;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.wishlist.domain.Wishlist;
import com.goormitrip.goormitrip.wishlist.dto.WishlistResponse;
import com.goormitrip.goormitrip.wishlist.service.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping
    public void addToWishlist(
            @RequestParam Long productId,
            @RequestAttribute("user") UserEntity user) {
        Product product = Product.builder().id(productId).build();
        wishlistService.addToWishlist(user, product);
    }

    @DeleteMapping
    public void removeFromWishlist(
            @RequestParam Long productId,
            @RequestAttribute("user") UserEntity user) {
        Product product = Product.builder().id(productId).build();
        wishlistService.removeFromWishlist(user, product);
    }

    @GetMapping
    public List<WishlistResponse> getWishlist(@RequestAttribute("user") UserEntity user) {
        List<Wishlist> wishlists = wishlistService.getWishlist(user);

        return wishlists.stream()
                .map(wishlist -> WishlistResponse.builder()
                        .id(wishlist.getId())
                        .productId(wishlist.getProduct().getId())
                        .productTitle(wishlist.getProduct().getTitle())
                        .thumbnail(wishlist.getProduct().getThumbnail())
                        .build())
                .toList();
    }
}
