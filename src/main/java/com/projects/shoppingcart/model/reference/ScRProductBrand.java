package com.projects.shoppingcart.model.reference;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_R_PRODUCT_BRAND")
public class ScRProductBrand {

    @Id
    @GeneratedValue(generator = "SC_R_PRODUCT_BRAND", strategy = GenerationType.IDENTITY)
    @Column(name = "BRAND_ID")
    private Long brandId;

    @Column(name = "BRAND_NAME", length = 100)
    private String name;

    @Column(name = "BRAND_DESC", length = 200)
    private String description;

}
