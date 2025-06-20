package com.goormitrip.goormitrip.wishlist.service.impl;

import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.wishlist.domain.Wishlist;
import com.goormitrip.goormitrip.wishlist.repository.WishlistRepository;
import com.goormitrip.goormitrip.wishlist.service.WishlistService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    @Override
    public void addToWishlist(UserEntity user, Product product) {
        if (wishlistRepository.existsByUserAndProduct(user, product)) {
            throw new IllegalStateException("이미 찜한 상품입니다.");
        }

        Wishlist wishlist = Wishlist.builder()
                .user(user)
                .product(product)
                .createdAt(LocalDateTime.now())
                .build();

        wishlistRepository.save(wishlist);
    }

    @Override
    public void removeFromWishlist(UserEntity user, Product product) {
        wishlistRepository.findByUserAndProduct(user, product)
                .ifPresent(wishlistRepository::delete);
    }

    @Override
    public List<Wishlist> getWishlist(UserEntity user) {
        return wishlistRepository.findByUser(user);
    }
}
