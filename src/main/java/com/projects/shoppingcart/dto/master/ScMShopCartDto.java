package com.projects.shoppingcart.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMShopCartDto implements Serializable {
    private Long shopCartId;
    private Integer productQty;

    private Long productId;
    private Long userId;
//    private ScMProductDto scMProduct;
//    private ScMUserDto scMUser;
}