package com.projects.shoppingcart.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CartResponseDto {
    private Long userId;
    private Float totalPrice;
    private Float totalDiscount;

    private List<CartItemResponseDto> items;
}