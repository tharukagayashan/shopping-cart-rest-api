package com.projects.shoppingcart.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMShopCartDto implements Serializable {
    private Long shopCartId;
    private Integer productQty;
    private LocalDate dateAdded;
    private LocalTime timeAdded;

    private Long productId;
    private Long userId;
}