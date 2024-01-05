package com.projects.shoppingcart.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMOrderDto {
    private Long orderId;
    private String orderNo;
    private LocalDate orderDate;
    private String orderTime;
    private Integer totalItem;
    private Float totalAmount;
    private Float totalDiscount;
    private Float totalTax;
    private Float totalPayable;
    private ScMUserDto scMUserDto;
}