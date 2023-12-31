package com.project.onlineshop.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMOrderProductDto {
    private Long orderItemId;
    private Integer productQty;
    private ScMOrderDto scMOrder;
    private ScMProductDto scMProduct;
}