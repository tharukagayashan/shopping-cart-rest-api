package com.project.onlineshop.dto.master;

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
    private ScMProductDto scMProduct;
    private ScMCustomerDto scMCustomer;
}