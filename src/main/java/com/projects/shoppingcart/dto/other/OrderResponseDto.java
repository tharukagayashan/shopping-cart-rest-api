package com.projects.shoppingcart.dto.other;

import com.projects.shoppingcart.dto.master.ScMOrderProductDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponseDto {
    private Long orderId;
    private String orderNo;
    private LocalDate orderDate;
    private LocalTime orderTime;
    private Integer totalItem;
    private Float totalAmount;
    private Float totalDiscount;
    private Float totalTax;
    private Float totalPayable;
    private Long userId;
    private Long statusId;
    private List<ScMOrderProductDto> products;
}
