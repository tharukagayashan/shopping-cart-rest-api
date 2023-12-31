package com.project.onlineshop.model.master;

import com.project.onlineshop.model.reference.ScRProductBrand;
import com.project.onlineshop.model.reference.ScRProductSubCategory;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_M_PRODUCT", indexes = {
        @Index(name = "SC_M_PRODUCT_PRODUCT_CODE_UNQ_idx", columnList = "PRODUCT_CODE", unique = true),
        @Index(name = "fk_SC_M_PRODUCT_PRODUCT_SUB_CATEGORY1_idx", columnList = "SUB_CAT_ID")
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

}
