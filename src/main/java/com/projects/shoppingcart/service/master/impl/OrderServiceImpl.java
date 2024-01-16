package com.projects.shoppingcart.service.master.impl;

import com.projects.shoppingcart.constants.HardCodeConstant;
import com.projects.shoppingcart.dao.master.*;
import com.projects.shoppingcart.dao.reference.ScRStatusRepository;
import com.projects.shoppingcart.dto.master.ScMOrderDto;
import com.projects.shoppingcart.dto.other.OrderCreateReqDto;
import com.projects.shoppingcart.dto.other.ProductDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMOrderMapper;
import com.projects.shoppingcart.model.master.*;
import com.projects.shoppingcart.model.reference.ScRStatus;
import com.projects.shoppingcart.service.master.OrderService;
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
public class OrderServiceImpl implements OrderService {

    private final ScMProductRepository productRepository;
    private final ScMUserRepository userRepository;
    private final ScRStatusRepository statusRepository;
    private final ScMOrderRepository orderRepository;
    private final ScMOrderProductRepository orderProductRepository;
    private final ScMShopCartRepository shopCartRepository;
    private final ScMOrderMapper orderMapper;

    public OrderServiceImpl(ScMProductRepository productRepository, ScMUserRepository userRepository, ScRStatusRepository statusRepository, ScMOrderRepository orderRepository, ScMOrderProductRepository orderProductRepository, ScMShopCartRepository shopCartRepository, ScMOrderMapper orderMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.shopCartRepository = shopCartRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public ResponseEntity<ScMOrderDto> createOrder(OrderCreateReqDto orderRequestDto) {
        try {
            Optional<ScMUser> optUser = userRepository.findById(orderRequestDto.getUserId());
            if (!optUser.isPresent()) {
                throw new BadRequestAlertException("User not found", "Cart", "createOrder");
            } else {
                ScMUser user = optUser.get();

                Optional<ScRStatus> optStatus = statusRepository.findById(HardCodeConstant.STATUS_NEW_ID);
                if (!optStatus.isPresent()) {
                    throw new BadRequestAlertException("Status not found", "Cart", "createOrder");
                }
                ScRStatus status = optStatus.get();

                int totalItems = 0;
                float totalAmount = 0;
                float totalDiscount = 0;
                float totalTax = 0;
                float totalPayable;

                for (ProductDto productDto : orderRequestDto.getProducts()) {
                    if (productDto.getProductId() == null) {
                        throw new BadRequestAlertException("productId null", "Cart", "createOrder");
                    } else {
                        Optional<ScMProduct> optProduct = productRepository.findById(productDto.getProductId());
                        if (!optProduct.isPresent()) {
                            throw new BadRequestAlertException("Product not found", "Cart", "createOrder");
                        }
                        ScMProduct product = optProduct.get();

                        totalItems += productDto.getQuantity();
                        totalAmount += productDto.getQuantity() * product.getPrice();
                        totalDiscount += productDto.getQuantity() * product.getPrice() * product.getDiscount() / 100;
                        totalTax += 0;
                    }
                }
                totalPayable = (totalAmount - totalDiscount) + totalTax;

                ScMOrder order = new ScMOrder();
                order.setOrderDate(LocalDate.now());
                order.setOrderTime(LocalTime.now());
                order.setTotalItem(totalItems);
                order.setTotalAmount(totalAmount);
                order.setTotalDiscount(totalDiscount);
                order.setTotalTax(totalTax);
                order.setTotalPayable(totalPayable);
                order.setScMUser(user);
                order.setScRStatus(status);
                order = orderRepository.save(order);
                if (order.getOrderId() == null) {
                    throw new BadRequestAlertException("Error while creating order", "Cart", "createOrder");
                } else {
                    String orderNo = "ORD" + LocalDate.now().getYear() + LocalDate.now().getMonthValue() + order.getOrderId();
                    order.setOrderNo(orderNo);
                    order = orderRepository.save(order);
                    log.info("Order created: {}", order.getOrderId());

                    List<ScMOrderProduct> orderProductList = new ArrayList<>();
                    for (ProductDto productDto : orderRequestDto.getProducts()) {
                        ScMProduct product = productRepository.findById(productDto.getProductId()).get();

                        ScMOrderProduct orderProduct = new ScMOrderProduct();
                        orderProduct.setProductQty(productDto.getQuantity());
                        orderProduct.setScMOrder(order);
                        orderProduct.setScMProduct(product);
                        orderProductList.add(orderProduct);
                    }
                    orderProductList = orderProductRepository.saveAll(orderProductList);
                    for (ScMOrderProduct orderProduct : orderProductList) {
                        if (orderProduct.getOrderItemId() == null) {
                            throw new BadRequestAlertException("Error while creating order", "Cart", "createOrder");
                        }
                    }

                    List<ScMShopCart> shopCartList = shopCartRepository.findAllByScMUser(user);
                    for (ScMShopCart shopCart : shopCartList) {
                        for (ProductDto productDto : orderRequestDto.getProducts()) {
                            if (shopCart.getScMProduct().getProductId().equals(productDto.getProductId())) {
                                if (shopCart.getProductQty() - productDto.getQuantity() > 0) {
                                    log.info("Updating cart: {}", shopCart.getShopCartId());
                                    log.info("There are {} items left", shopCart.getProductQty() - productDto.getQuantity());
                                    shopCart.setProductQty(shopCart.getProductQty() - productDto.getQuantity());
                                    shopCartRepository.save(shopCart);
                                } else {
                                    log.info("Deleting cart: {}", shopCart.getShopCartId());
                                    log.info("There are no items left");
                                    shopCartRepository.deleteById(shopCart.getShopCartId());
                                }
                            }
                        }
                    }
                    return ResponseEntity.ok(orderMapper.toDto(order));
                }
            }
        } catch (Exception e) {
            log.error("Error while creating order: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "createOrder");
        }
    }

}
