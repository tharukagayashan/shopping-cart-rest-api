package com.projects.shoppingcart.model.master;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_SHOP_CART", indexes = {
        @Index(name = "fk_SC_M_SHOP_CART_SC_M_PRODUCT1_idx", columnList = "PRODUCT_ID"),
        @Index(name = "fk_SC_M_SHOP_CART_SC_M_USER1_idx", columnList = "USER_ID")
})
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
    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID", nullable = false)
    private ScMUser scMUser;

}
