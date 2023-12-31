package com.project.onlineshop.model.master;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_SHOP_CART")
public class ScMShopCart {

    @Id
    @GeneratedValue(generator = "SC_M_SHOP_CART", strategy = javax.persistence.GenerationType.IDENTITY)
    private Long shopCartId;

    @Column(name = "PRODUCT_QTY")
    private Integer productQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
    private ScMProduct scMProduct;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", referencedColumnName = "CUSTOMER_ID", nullable = false)
    private ScMCustomer scMCustomer;

}
