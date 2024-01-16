package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.dto.other.AddToCartDto;
import com.projects.shoppingcart.dto.other.CartResponseDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<ScMShopCartDto> addToCart(AddToCartDto addToCartDto);

    ResponseEntity<Long> removeFromCart(Long shopCartId);

    ResponseEntity<CartResponseDto> getCart(Long userId);
}
