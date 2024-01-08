package com.projects.shoppingcart.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddToCartDto {
    @NotNull
    private Long productId;
    @NotNull
    private Integer productQty;
    @NotNull
    private Long userId;
}