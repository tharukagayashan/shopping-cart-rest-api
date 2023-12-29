package com.project.onlineshop.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_TYPE", indexes = {
        @Index(name = "PRODUCT_TYPE_TYPE_CODE_UNQ_idx", columnList = "TYPE_CODE", unique = true)
})
public class ProductType {

    @Id
    @GeneratedValue(generator = "PRODUCT_TYPE", strategy = GenerationType.IDENTITY)
    @Column(name = "TYPE_ID")
    private Long typeId;

    @Column(name = "TYPE_NAME", length = 80)
    private String name;

    @Column(name = "TYPE_DESC", length = 200)
    private String description;

    @Column(name = "TYPE_CODE", length = 10)
    private String code;

}
