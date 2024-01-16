package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMOrderDto;
import com.projects.shoppingcart.dto.other.OrderCreateReqDto;
import org.springframework.http.ResponseEntity;

public interface OrderService {
    ResponseEntity<ScMOrderDto> createOrder(OrderCreateReqDto orderRequestDto);
}
