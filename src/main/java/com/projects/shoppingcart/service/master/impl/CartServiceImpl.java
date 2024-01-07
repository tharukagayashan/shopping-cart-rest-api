package com.projects.shoppingcart.service.master.impl;

import com.projects.shoppingcart.dao.master.ScMProductRepository;
import com.projects.shoppingcart.dao.master.ScMShopCartRepository;
import com.projects.shoppingcart.dao.master.ScMUserRepository;
import com.projects.shoppingcart.dto.master.ScMShopCartDto;
import com.projects.shoppingcart.dto.other.AddToCartDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMShopCartMapper;
import com.projects.shoppingcart.model.master.ScMProduct;
import com.projects.shoppingcart.model.master.ScMShopCart;
import com.projects.shoppingcart.model.master.ScMUser;
import com.projects.shoppingcart.service.master.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
                ScMShopCart shopCart = new ScMShopCart();
                shopCart.setProductQty(addToCartDto.getProductQty());
                shopCart.setScMProduct(optProduct.get());
                shopCart.setScMUser(optUser.get());
                shopCart = shopCartRepository.save(shopCart);
                if (shopCart.getShopCartId() == null) {
                    throw new BadRequestAlertException("Error while adding to cart", "Cart", "addToCart");
                } else {
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
    public ResponseEntity<List<ScMShopCartDto>> getCart(Long userId) {
        try {
            Optional<ScMUser> optUser = userRepository.findById(userId);
            if (!optUser.isPresent()) {
                throw new BadRequestAlertException("User not found", "Cart", "getCart");
            } else {
                List<ScMShopCartDto> shopCartDtoList = new ArrayList<>();
                List<ScMShopCart> shopCartList = shopCartRepository.findAllByScMUser(optUser.get());
                if (!shopCartList.isEmpty()) {
                    shopCartDtoList = shopCartMapper.entityListToDtoList(shopCartList);
                }
                return ResponseEntity.ok(shopCartDtoList);
            }
        } catch (Exception e) {
            log.error("Error while getting cart: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "getCart");
        }
    }

    @Override
    public ResponseEntity<String> checkout(List<ScMShopCartDto> shopCartDtoList) {
        try {
            if (shopCartDtoList.isEmpty()) {
                throw new BadRequestAlertException("Cart is empty", "Cart", "checkout");
            } else {

                shopCartDtoList.forEach(shopCartDto -> {
                    Optional<ScMProduct> optProduct = productRepository.findById(shopCartDto.getProductId());
                    if (optProduct.isPresent()) {
                        ScMProduct product = optProduct.get();
                        product.setQuantity(product.getQuantity() - shopCartDto.getProductQty());
                        productRepository.save(product);
                    }
                });

                shopCartDtoList.forEach(shopCartDto -> {
                    Optional<ScMShopCart> optShopCart = shopCartRepository.findById(shopCartDto.getShopCartId());
                    if (optShopCart.isPresent()) {
                        shopCartRepository.deleteById(shopCartDto.getShopCartId());
                    }
                });

                return ResponseEntity.ok("Checkout successful");
            }
        } catch (Exception e) {
            log.error("Error while checking out: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "checkout");
        }
    }
}
