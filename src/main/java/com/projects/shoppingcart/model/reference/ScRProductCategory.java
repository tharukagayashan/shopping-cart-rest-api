package com.projects.shoppingcart.model.reference;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_R_PRODUCT_CATEGORY", indexes = {
        @Index(name = "SC_R_PRODUCT_CATEGORY_CAT_CODE_UNQ_idx", columnList = "CAT_CODE", unique = true),
        @Index(name = "fk_SC_R_PRODUCT_CATEGORY_SC_R_PRODUCT_TYPE1_idx", columnList = "TYPE_ID")
})
public class ScRProductCategory {

    @Id
    @GeneratedValue(generator = "SC_R_PRODUCT_CATEGORY", strategy = GenerationType.IDENTITY)
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
    private ScRProductType scRProductType;

}