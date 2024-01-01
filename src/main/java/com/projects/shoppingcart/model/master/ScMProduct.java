package com.projects.shoppingcart.model.master;

import com.projects.shoppingcart.model.reference.ScRProductBrand;
import com.projects.shoppingcart.model.reference.ScRProductSubCategory;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_M_PRODUCT", indexes = {
        @Index(name = "SC_M_PRODUCT_PRODUCT_CODE_UNQ_idx", columnList = "PRODUCT_CODE", unique = true),
        @Index(name = "fk_SC_M_PRODUCT_SC_R_PRODUCT_BRAND1_idx", columnList = "BRAND_ID"),
        @Index(name = "fk_SC_M_PRODUCT_SC_R_PRODUCT_SUB_CATEGORY1_idx", columnList = "SUB_CAT_ID")
})
public class ScMProduct {

    @Id
    @GeneratedValue(generator = "SC_M_PRODUCT", strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long productId;

    @Column(name = "PRODUCT_NAME", length = 100)
    private String name;

    @Column(name = "PRODUCT_DESC", length = 200)
    private String description;

    @Column(name = "PRODUCT_CODE", length = 10)
    private String code;

    @Column(name = "PRODUCT_PRICE")
    private Float price;

    @Column(name = "PRODUCT_QUANTITY")
    private Integer quantity;

    @Column(name = "PRODUCT_IMAGE")
    private String image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SUB_CAT_ID", nullable = false, referencedColumnName = "SUB_CAT_ID")
    private ScRProductSubCategory scRProductSubCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BRAND_ID", nullable = false, referencedColumnName = "BRAND_ID")
    private ScRProductBrand scRProductBrand;

    @OneToMany(mappedBy = "scMProduct", fetch = FetchType.LAZY)
    private List<ScMOrderProduct> scMOrderProducts;

}
