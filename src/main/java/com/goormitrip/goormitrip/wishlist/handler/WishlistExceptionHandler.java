package com.goormitrip.goormitrip.wishlist.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.wishlist.exception.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.product")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class WishlistExceptionHandler {

    @ExceptionHandler(AlreadyWishlistedException.class)
    public ResponseEntity<?> handleAlreadyWishlisted(AlreadyWishlistedException ex) {
        log.warn("이미 찜된 상품: {}", ex.getMessage());
        return ResponseEntity
                .status(ex.getErrorCode().getStatus())
                .body(ApiResponse.error(ex));
    }

    @ExceptionHandler(WishlistNotFoundException.class)
    public ResponseEntity<?> handleWishlistNotFound(WishlistNotFoundException ex) {
        log.warn("찜 항목 없음: {}", ex.getMessage());
        return ResponseEntity
                .status(ex.getErrorCode().getStatus())
                .body(ApiResponse.error(ex));
    }

}