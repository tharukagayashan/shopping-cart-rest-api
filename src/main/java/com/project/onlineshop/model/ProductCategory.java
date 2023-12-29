package com.project.onlineshop.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_CATEGORY", indexes = {
        @Index(name = "PRODUCT_CATEGORY_CAT_CODE_UNQ_idx", columnList = "CAT_CODE", unique = true),
        @Index(name = "fk_PRODUCT_CATEGORY_ST_R_PR_TYPE_TYPE_ID_idx", columnList = "TYPE_ID")
})
public class ProductCategory {

    @Id
    @GeneratedValue(generator = "PRODUCT_CATEGORY", strategy = GenerationType.IDENTITY)
    @Column(name = "CAT_ID")
    private Long categoryId;

    @Column(name = "CAT_NAME", length = 100)
    private String name;

    @Column(name = "CAT_DESC", length = 200)
    private String description;

    @Column(name = "CAT_CODE", length = 10)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID", nullable = false, referencedColumnName = "TYPE_ID")
    private ProductType productType;

}