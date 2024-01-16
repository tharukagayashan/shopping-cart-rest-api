package com.projects.shoppingcart.service.master.impl;

import com.projects.shoppingcart.dao.master.ScMProductRepository;
import com.projects.shoppingcart.dao.master.ScMShopCartRepository;
import com.projects.shoppingcart.dao.master.ScMUserRepository;
import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.dto.other.AddToCartDto;
import com.projects.shoppingcart.dto.other.CartItemResponseDto;
import com.projects.shoppingcart.dto.other.CartResponseDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMShopCartMapper;
import com.projects.shoppingcart.model.master.ScMProduct;
import com.projects.shoppingcart.model.master.ScMShopCart;
import com.projects.shoppingcart.model.master.ScMUser;
import com.projects.shoppingcart.service.master.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CartServiceImpl implements CartService {
    private final ScMShopCartRepository shopCartRepository;
    private final ScMShopCartMapper shopCartMapper;
    private final ScMProductRepository productRepository;
    private final ScMUserRepository userRepository;

    public CartServiceImpl(ScMShopCartRepository shopCartRepository, ScMShopCartMapper shopCartMapper, ScMProductRepository productRepository, ScMUserRepository userRepository) {
        this.shopCartRepository = shopCartRepository;
        this.shopCartMapper = shopCartMapper;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    private static CartItemResponseDto getCartItemResponseDto(ScMShopCart shopCart, float productTotalPrice, float discount) {
        CartItemResponseDto cartItemResponseDto = new CartItemResponseDto();
        cartItemResponseDto.setProductId(shopCart.getScMProduct().getProductId());
        cartItemResponseDto.setProductName(shopCart.getScMProduct().getName());
        cartItemResponseDto.setProductQty(shopCart.getProductQty());
        cartItemResponseDto.setPricePerUnit(shopCart.getScMProduct().getPrice());
        cartItemResponseDto.setImage(shopCart.getScMProduct().getImage());
        cartItemResponseDto.setDateAdded(shopCart.getDateAdded());
        cartItemResponseDto.setTimeAdded(shopCart.getTimeAdded());
        cartItemResponseDto.setTotalPrice(productTotalPrice);
        cartItemResponseDto.setDiscount(discount);
        return cartItemResponseDto;
    }

    @Override
    public ResponseEntity<ScMShopCartDto> addToCart(AddToCartDto addToCartDto) {
        try {
            Optional<ScMProduct> optProduct = productRepository.findById(addToCartDto.getProductId());
            Optional<ScMUser> optUser = userRepository.findById(addToCartDto.getUserId());
            if (!optProduct.isPresent()) {
                throw new BadRequestAlertException("Product not found", "Cart", "addToCart");
            } else if (!optUser.isPresent()) {
                throw new BadRequestAlertException("User not found", "Cart", "addToCart");
            } else {
                Optional<ScMShopCart> optShopCart = shopCartRepository.findByScMUserAndScMProduct(optUser.get(), optProduct.get());
                if (!optShopCart.isPresent()) {
                    ScMShopCart shopCart = new ScMShopCart();
                    shopCart.setProductQty(addToCartDto.getProductQty());
                    shopCart.setScMProduct(optProduct.get());
                    shopCart.setScMUser(optUser.get());
                    shopCart.setDateAdded(LocalDate.now());
                    shopCart.setTimeAdded(LocalTime.now());
                    shopCart = shopCartRepository.save(shopCart);
                    if (shopCart.getShopCartId() == null) {
                        throw new BadRequestAlertException("Error while adding to cart", "Cart", "addToCart");
                    } else {
                        return ResponseEntity.ok(shopCartMapper.toDto(shopCart));
                    }
                } else {
                    ScMShopCart shopCart = optShopCart.get();
                    shopCart.setProductQty(shopCart.getProductQty() + addToCartDto.getProductQty());
                    shopCart.setDateAdded(LocalDate.now());
                    shopCart.setTimeAdded(LocalTime.now());
                    shopCart = shopCartRepository.save(shopCart);
                    log.info("Cart existing item updated: {}", shopCart.getShopCartId());
                    return ResponseEntity.ok(shopCartMapper.toDto(shopCart));
                }
            }
        } catch (Exception e) {
            log.error("Error while adding to cart: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "addToCart");
        }
    }

    @Override
    public ResponseEntity<Long> removeFromCart(Long shopCartId) {
        try {
            if (shopCartId == null) {
                throw new BadRequestAlertException("Invalid cartId", "Cart", "removeFromCart");
            } else {
                Optional<ScMShopCart> optShopCart = shopCartRepository.findById(shopCartId);
                if (!optShopCart.isPresent()) {
                    throw new BadRequestAlertException("Cart Item not found", "Cart", "removeFromCart");
                } else {
                    shopCartRepository.deleteById(shopCartId);
                    if (shopCartRepository.findById(shopCartId).isPresent()) {
                        throw new BadRequestAlertException("Error while removing from cart", "Cart", "removeFromCart");
                    } else {
                        log.info("Removed from cart: {}", shopCartId);
                        return ResponseEntity.ok(shopCartId);
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while removing from cart: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "removeFromCart");
        }
    }

    @Override
    public ResponseEntity<CartResponseDto> getCart(Long userId) {
        try {
            Optional<ScMUser> optUser = userRepository.findById(userId);
            if (!optUser.isPresent()) {
                throw new BadRequestAlertException("User not found", "Cart", "getCart");
            } else {
                CartResponseDto cartResponseDto = new CartResponseDto();
                List<ScMShopCart> shopCartList = shopCartRepository.findAllByScMUser(optUser.get());
                float totalPrice = 0;
                float totalDiscount = 0;
                if (!shopCartList.isEmpty()) {
                    List<CartItemResponseDto> cartItemResponseDtoList = new ArrayList<>();
                    for (ScMShopCart shopCart : shopCartList) {

                        float productTotalPrice = shopCart.getScMProduct().getPrice() * shopCart.getProductQty();
                        float discount = productTotalPrice * shopCart.getScMProduct().getDiscount() / 100;

                        totalPrice += productTotalPrice;
                        totalDiscount += discount;

                        CartItemResponseDto cartItemResponseDto = getCartItemResponseDto(shopCart, productTotalPrice, discount);
                        cartItemResponseDtoList.add(cartItemResponseDto);
                    }
                    cartResponseDto.setUserId(userId);
                    cartResponseDto.setItems(cartItemResponseDtoList);
                    cartResponseDto.setTotalPrice(totalPrice);
                    cartResponseDto.setTotalDiscount(totalDiscount);

                    return ResponseEntity.ok(cartResponseDto);
                } else {
                    throw new BadRequestAlertException("Cart is empty", "Cart", "getCart");
                }
            }
        } catch (Exception e) {
            log.error("Error while getting cart: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "getCart");
        }
    }

}