package com.projects.shoppingcart.model.master;

import lombok.*;

import javax.persistence.*;

@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "SC_M_ORDER_PRODUCT", indexes = {
        @Index(name = "fk_SC_M_ORDER_PRODUCT_SC_M_ORDER1_idx", columnList = "ORDER_ID"),
        @Index(name = "fk_SC_M_ORDER_PRODUCT_SC_M_PRODUCT1_idx", columnList = "PRODUCT_ID")
})
public class ScMOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SC_M_ORDER_PRODUCT")
    @Column(name = "ORDER_ITEM_ID", nullable = false)
    private Long orderItemId;

    @Column(name = "PRODUCT_QTY")
    private Integer productQty;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ORDER_ID", referencedColumnName = "ORDER_ID")
    private ScMOrder scMOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID", nullable = false)
    private ScMProduct scMProduct;

}
