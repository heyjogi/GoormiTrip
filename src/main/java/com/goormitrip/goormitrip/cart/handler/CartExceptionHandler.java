package com.goormitrip.goormitrip.cart.handler;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.goormitrip.goormitrip.global.util.response.ApiResponse;
import com.goormitrip.goormitrip.cart.exception.*;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice(basePackages = "com.goormitrip.goormitrip.product")
@Order(Ordered.HIGHEST_PRECEDENCE)
public class CartExceptionHandler {

    @ExceptionHandler(CartItemNotFoundException.class)
	public ResponseEntity<?> handleCartItemNotFound(CartItemNotFoundException ex) {
		log.warn("Cart item not found: {}", 
    	return ResponseEntity
    		.status(ex.getErrorCode().getStatus())
        	.body(ApiResponse.error(ex));
        
                
                @ExceptionHandler(CartNotFoun
     

           return ResponseEntity
               .status(ex.getErrorCode().getStatus())
                  .body(ApiResponse.error(ex));
          }

    @ExceptionHandler(ForbiddenEx

    return ResponseEntity.status(ex.getErrorCode().getStatus()).body(ApiResponse.error(ex));
}
