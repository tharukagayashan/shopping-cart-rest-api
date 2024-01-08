package com.projects.shoppingcart.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartItemResponseDto {
    private Long productId;
    private String productName;
    private Integer productQty;
    private Float pricePerUnit;
    private String image;
    private LocalDate dateAdded;
    private LocalTime timeAdded;
    private Float totalPrice;
    private Float discount;
}