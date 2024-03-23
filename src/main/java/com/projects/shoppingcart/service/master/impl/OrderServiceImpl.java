package com.projects.shoppingcart.service.master.impl;

import com.projects.shoppingcart.constants.HardCodeConstant;
import com.projects.shoppingcart.dao.master.*;
import com.projects.shoppingcart.dao.reference.ScRStatusRepository;
import com.projects.shoppingcart.dto.master.ScMOrderDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.dto.miscellaneous.PaginationDto;
import com.projects.shoppingcart.dto.other.OrderCreateReqDto;
import com.projects.shoppingcart.dto.other.OrderProcessReqDto;
import com.projects.shoppingcart.dto.other.OrderResponseDto;
import com.projects.shoppingcart.dto.other.ProductDto;
import com.projects.shoppingcart.error.BadRequestAlertException;
import com.projects.shoppingcart.mapper.master.ScMOrderMapper;
import com.projects.shoppingcart.mapper.master.ScMOrderProductMapper;
import com.projects.shoppingcart.model.master.*;
import com.projects.shoppingcart.model.reference.ScRStatus;
import com.projects.shoppingcart.service.master.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    private final ScMOrderProductMapper orderProductMapper;

    public OrderServiceImpl(ScMProductRepository productRepository, ScMUserRepository userRepository, ScRStatusRepository statusRepository, ScMOrderRepository orderRepository, ScMOrderProductRepository orderProductRepository, ScMShopCartRepository shopCartRepository, ScMOrderMapper orderMapper, ScMOrderProductMapper orderProductMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.statusRepository = statusRepository;
        this.orderRepository = orderRepository;
        this.orderProductRepository = orderProductRepository;
        this.shopCartRepository = shopCartRepository;
        this.orderMapper = orderMapper;
        this.orderProductMapper = orderProductMapper;
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

    @Override
    public ResponseEntity<ApiResponseDto<List<ScMOrderDto>>> getOrderList(Integer page, Integer per_page, String sort, String direction, String search, String statusId, String userId) {
        try {
            Page<ScMOrder> dbData;
            if (direction.equalsIgnoreCase("asc")) {
                dbData = orderRepository.getAllOrder(search, statusId, userId, PageRequest.of(page, per_page, Sort.by(Sort.Direction.ASC, sort)));
            } else {
                dbData = orderRepository.getAllOrder(search, statusId, userId, PageRequest.of(page, per_page, Sort.by(Sort.Direction.DESC, sort)));
            }
            ApiResponseDto<List<ScMOrderDto>> responseDto = new ApiResponseDto<>();
            PaginationDto paginationDto = new PaginationDto();
            paginationDto.setTotal(dbData.getTotalElements());
            responseDto.setPagination(paginationDto);
            responseDto.setResult(orderMapper.entityListToDtoList(dbData.getContent()));
            return ResponseEntity.ok(responseDto);
        } catch (Exception e) {
            log.error("Error while getting order list: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "getOrderList");
        }
    }

    @Override
    public ResponseEntity<OrderResponseDto> getOrder(Long orderId) {
        try {
            if (orderId == null) {
                throw new BadRequestAlertException("Invalid orderId", "Cart", "getOrder");
            } else {
                Optional<ScMOrder> optOrder = orderRepository.findById(orderId);
                if (!optOrder.isPresent()) {
                    throw new BadRequestAlertException("Order not found", "Cart", "getOrder");
                } else {

                    List<ScMOrderProduct> orderProductList = orderProductRepository.findByScMOrderOrderId(orderId);

                    ScMOrder order = optOrder.get();
                    OrderResponseDto orderResponseDto = new OrderResponseDto();
                    orderResponseDto.setOrderId(order.getOrderId());
                    orderResponseDto.setOrderNo(order.getOrderNo());
                    orderResponseDto.setOrderDate(order.getOrderDate());
                    orderResponseDto.setOrderTime(order.getOrderTime());
                    orderResponseDto.setTotalItem(order.getTotalItem());
                    orderResponseDto.setTotalAmount(order.getTotalAmount());
                    orderResponseDto.setTotalDiscount(order.getTotalDiscount());
                    orderResponseDto.setTotalTax(order.getTotalTax());
                    orderResponseDto.setTotalPayable(order.getTotalPayable());
                    orderResponseDto.setUserId(order.getScMUser().getUserId());
                    orderResponseDto.setStatusId(order.getScRStatus().getStatusId());
                    orderResponseDto.setProducts(orderProductMapper.entityListToDtoList(orderProductList));
                    return ResponseEntity.ok(orderResponseDto);
                }
            }
        } catch (Exception e) {
            log.error("Error while getting order: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "getOrder");
        }
    }

    @Override
    public ResponseEntity<ScMOrderDto> cancelOrder(Long orderId) {
        try {
            if (orderId == null) {
                throw new BadRequestAlertException("Invalid orderId", "Cart", "cancelOrder");
            } else {
                Optional<ScMOrder> optOrder = orderRepository.findById(orderId);
                if (!optOrder.isPresent()) {
                    throw new BadRequestAlertException("Order not found", "Cart", "cancelOrder");
                } else {
                    ScMOrder order = optOrder.get();
                    Optional<ScRStatus> optStatus = statusRepository.findById(HardCodeConstant.STATUS_CANCELED_ID);
                    if (!optStatus.isPresent()) {
                        throw new BadRequestAlertException("Status not found", "Cart", "cancelOrder");
                    }
                    ScRStatus status = optStatus.get();
                    order.setScRStatus(status);
                    order = orderRepository.save(order);
                    if (!order.getScRStatus().getStatusId().equals(HardCodeConstant.STATUS_CANCELED_ID)) {
                        throw new BadRequestAlertException("Error while canceling order", "Cart", "cancelOrder");
                    } else {
                        log.info("Order canceled: {}", order.getOrderId());
                        return ResponseEntity.ok(orderMapper.toDto(order));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while canceling order: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "cancelOrder");
        }
    }

    @Override
    public ResponseEntity<ScMOrderDto> processOrder(OrderProcessReqDto orderProcessReqDto) {
        try {
            if (orderProcessReqDto.getOrderId() == null) {
                throw new BadRequestAlertException("Invalid orderId", "Cart", "processOrder");
            } else {
                Optional<ScMOrder> optOrder = orderRepository.findById(orderProcessReqDto.getOrderId());
                if (!optOrder.isPresent()) {
                    throw new BadRequestAlertException("Order not found", "Cart", "processOrder");
                } else {
                    ScMOrder order = optOrder.get();
                    Optional<ScRStatus> optStatus = statusRepository.findById(orderProcessReqDto.getStatusId());
                    if (!optStatus.isPresent()) {
                        throw new BadRequestAlertException("Status not found", "Cart", "processOrder");
                    }
                    ScRStatus status = optStatus.get();
                    order.setScRStatus(status);
                    order = orderRepository.save(order);
                    if (!order.getScRStatus().getStatusId().equals(orderProcessReqDto.getStatusId())) {
                        throw new BadRequestAlertException("Error while processing order", "Cart", "processOrder");
                    } else {
                        log.info("Order processed. Order ID: {} and Order Status: {}", order.getOrderId(), order.getScRStatus().getName());
                        return ResponseEntity.ok(orderMapper.toDto(order));
                    }
                }
            }
        } catch (Exception e) {
            log.error("Error while processing order: {}", e.getMessage());
            throw new BadRequestAlertException(e.getMessage(), "Cart", "processOrder");
        }
    }

}
