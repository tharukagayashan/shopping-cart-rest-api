package com.project.onlineshop.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_SUB_CATEGORY", indexes = {
        @Index(name = "PRODUCT_SUB_CATEGORY_SUB_CAT_CODE_UNQ_idx", columnList = "SUB_CAT_CODE", unique = true),
        @Index(name = "fk_PRODUCT_SUB_CATEGORY_ST_R_CATEGORY_CAT_ID_idx", columnList = "CAT_ID")})
public class ProductSubCategory {

    @Id
    @GeneratedValue(generator = "PRODUCT_SUB_CATEGORY", strategy = GenerationType.IDENTITY)
    @Column(name = "SUB_CAT_ID")
    private Long subCategoryId;

    @Column(name = "SUB_CAT_NAME", length = 100)
    private String name;

    @Column(name = "SUB_CAT_DESC", length = 200)
    private String description;

    @Column(name = "SUB_CAT_CODE", length = 10)
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CAT_ID", nullable = false, referencedColumnName = "CAT_ID")
    private ProductCategory productCategory;

}
