package com.goormitrip.goormitrip.cart.repository;

import com.goormitrip.goormitrip.cart.domain.Cart;
import com.goormitrip.goormitrip.user.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    Optional<Cart> findByUser(UserEntity user);
}
