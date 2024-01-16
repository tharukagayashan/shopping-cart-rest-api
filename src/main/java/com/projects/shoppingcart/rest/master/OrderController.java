package com.projects.shoppingcart.rest.master;

import com.projects.shoppingcart.dto.master.ScMOrderDto;
import com.projects.shoppingcart.dto.miscellaneous.ApiResponseDto;
import com.projects.shoppingcart.dto.other.OrderCreateReqDto;
import com.projects.shoppingcart.dto.other.OrderResponseDto;
import com.projects.shoppingcart.service.master.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Validated
@RestController
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<ScMOrderDto> createOrder(@Valid @RequestBody OrderCreateReqDto orderRequestDto) {
        return orderService.createOrder(orderRequestDto);
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<ScMOrderDto>>> getOrderList(
            @RequestParam(value = "0", required = false) Integer page,
            @RequestParam(value = "10", required = false) Integer per_page,
            @RequestParam(value = "orderId", required = false) String sort,
            @RequestParam(value = "asc", required = false) String direction,
            @RequestParam(value = "", required = false) String search,
            @RequestParam(required = false) String statusId,
            @RequestParam(required = false) String userId
    ) {
        return orderService.getOrderList(page, per_page, sort, direction, search, statusId, userId);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> getOrder(@PathVariable("orderId") Long orderId) {
        return orderService.getOrder(orderId);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<ScMOrderDto> cancelOrder(@PathVariable("orderId") Long orderId) {
        return orderService.cancelOrder(orderId);
    }

}