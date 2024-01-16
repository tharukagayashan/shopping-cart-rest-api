package com.projects.shoppingcart.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMOrderProductDto {
    private Long orderItemId;
    private Integer productQty;

    private Long orderId;
    private Long productId;

    private ScMOrderDto scMOrder;
    private ScMProductDto scMProduct;
}