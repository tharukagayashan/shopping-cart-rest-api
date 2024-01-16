package com.projects.shoppingcart.service.master;

import com.projects.shoppingcart.dto.master.ScMOrderDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.dto.other.OrderCreateReqDto;
import com.projects.shoppingcart.dto.other.OrderResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface OrderService {
    ResponseEntity<ScMOrderDto> createOrder(OrderCreateReqDto orderRequestDto);
    ResponseEntity<ApiResponseDto<List<ScMOrderDto>>> getOrderList(Integer page, Integer per_page, String sort, String direction, String search, String statusId, String userId);
    ResponseEntity<OrderResponseDto> getOrder(Long orderId);

    ResponseEntity<ScMOrderDto> cancelOrder(Long orderId);
}
