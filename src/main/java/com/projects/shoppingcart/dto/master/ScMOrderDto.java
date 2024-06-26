package com.projects.shoppingcart.dto.master;

import com.projects.shoppingcart.dto.reference.ScRStatusDto;
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

    private Long statusId;
    private Long userId;

    private ScRStatusDto scRStatus;
    private ScMUserDto scMUser;
}