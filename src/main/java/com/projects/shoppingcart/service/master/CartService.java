package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.dto.other.AddToCartDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CartService {
    ResponseEntity<ScMShopCartDto> addToCart(AddToCartDto addToCartDto);
    ResponseEntity<Long> removeFromCart(Long shopCartId);
    ResponseEntity<List<ScMShopCartDto>> getCart(Long userId);
    ResponseEntity<String> checkout(List<ScMShopCartDto> shopCartDtoList);
}
