package com.projects.shoppingcart.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMOrderDto {
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

    private ScMUserDto scMUserDto;
}