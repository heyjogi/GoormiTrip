package com.goormitrip.goormitrip.cart.service.impl;

import com.goormitrip.goormitrip.cart.domain.Cart;
import com.goormitrip.goormitrip.cart.domain.CartItem;
import com.goormitrip.goormitrip.cart.dto.CartItemResponse;
import com.goormitrip.goormitrip.cart.repository.CartItemRepository;
import com.goormitrip.goormitrip.cart.repository.CartRepository;
import com.goormitrip.goormitrip.cart.service.CartService;
import com.goormitrip.goormitrip.cart.exception.CartItemNotFoundException;
import com.goormitrip.goormitrip.product.domain.Product;
import com.goormitrip.goormitrip.user.domain.UserEntity;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;

    @Override
    public void addToCart(UserEntity user, Product product, int peopleCount, String travelDate) {
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> cartRepository.save(
                        Cart.builder()
                                .user(user)
                                .createdAt(LocalDateTime.now())
                                .build()));

        CartItem cartItem = CartItem.builder()
                .cart(cart)
                .product(product)
                .peopleCount(peopleCount)
                .travelDate(LocalDate.parse(travelDate))
                .createdAt(LocalDateTime.now())
                .build();

        cartItemRepository.save(cartItem);
    }

    @Override
    public void removeFromCartItem(Long itemId, UserEntity user) {
        CartItem item = cartItemRepository.findById(itemId)
                .orElseThrow(() -> new CartItemNotFoundException(itemId));

        if (!item.getCart().getUser().getId().equals(user.getId())) {
            throw new ForbiddenException("해당 장바구니 항목에 대한 삭제 권한이 없습니다.");
        }

        cartItemRepository.delete(item);
    }

    @Override
    public List<CartItem> getCartItems(UserEntity user) {
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found"));

        return cartItemRepository.findByCart(cart);
    }

    @Override
    public List<CartItemResponse> getCartItemResponses(UserEntity user) {
        List<CartItem> items = getCartItems(user);
        return items.stream()
                .map(item -> CartItemResponse.builder()
                        .cartItemId(item.getId())
                        .productId(item.getProduct().getId())
                        .productTitle(item.getProduct().getTitle())
                        .productThumbnail(item.getProduct().getThumbnail())
                        .price(item.getProduct().getPrice())
                        .peopleCount(item.getPeopleCount())
                        .travelDate(item.getTravelDate().toString())
                        .build())
                .toList();
    }
}