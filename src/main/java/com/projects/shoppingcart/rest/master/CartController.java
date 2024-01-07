package com.projects.shoppingcart.rest.master;

import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.dto.other.AddToCartDto;
import com.projects.shoppingcart.service.master.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ScMShopCartDto> addToCart(AddToCartDto addToCartDto) {
        return cartService.addToCart(addToCartDto);
    }

    @DeleteMapping("/{shopCartId}")
    public ResponseEntity<Long> removeFromCart(@PathVariable Long shopCartId) {
        return cartService.removeFromCart(shopCartId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<ScMShopCartDto>> getCart(@PathVariable("userId") Long userId) {
        return cartService.getCart(userId);
    }

    @PostMapping("/checkout")
    public ResponseEntity<String> checkout(@RequestBody List<ScMShopCartDto> shopCartDtoList) {
        return cartService.checkout(shopCartDtoList);
    }

}