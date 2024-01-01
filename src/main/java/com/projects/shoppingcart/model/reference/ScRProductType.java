package com.projects.shoppingcart.model.reference;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_R_PRODUCT_TYPE", indexes = {
        @Index(name = "SC_R_PRODUCT_TYPE_TYPE_CODE_UNQ_idx", columnList = "TYPE_CODE", unique = true)
})
public class ScRProductType {

    @Id
    @GeneratedValue(generator = "SC_R_PRODUCT_TYPE", strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_ID")
    private Long typeId;

    @Column(name = "TYPE_NAME", length = 80)
    private String name;

    @Column(name = "TYPE_DESC", length = 200)
    private String description;

    @Column(name = "TYPE_CODE", length = 10)
    private String code;

}
