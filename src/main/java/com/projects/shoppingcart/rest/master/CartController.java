package com.projects.shoppingcart.rest.master;

import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.dto.other.AddToCartDto;
import com.projects.shoppingcart.dto.other.CartResponseDto;
import com.projects.shoppingcart.service.master.CartService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<ScMShopCartDto> addToCart(@RequestBody AddToCartDto addToCartDto) {
        return cartService.addToCart(addToCartDto);
    }

    @DeleteMapping("/{shopCartId}")
    public ResponseEntity<Long> removeFromCart(@PathVariable Long shopCartId) {
        return cartService.removeFromCart(shopCartId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<CartResponseDto> getCart(@PathVariable("userId") Long userId) {
        return cartService.getCart(userId);
    }

}