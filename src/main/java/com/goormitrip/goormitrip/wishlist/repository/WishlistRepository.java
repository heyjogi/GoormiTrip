package com.goormitrip.goormitrip.wishlist.repository;

import com.goormitrip.goormitrip.user.domain.UserEntity;
import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.wishlist.domain.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {
    List<Wishlist> findByUser(UserEntity user);

    Optional<Wishlist> findByUserAndProduct(UserEntity user, Product product);

    boolean existsByUserAndProduct(UserEntity user, Product product);
}
