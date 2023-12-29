package com.project.onlineshop.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_VARIABLE_MAPPING")
public class ProductVariableMapping {

    @Id
    @GeneratedValue(generator = "PRODUCT_VARIABLE_MAPPING", strategy = GenerationType.IDENTITY)
    @Column(name = "VARIABLE_MAPPING_ID")
    private Long variableMappingId;

    @Column(name = "VARIABLE_VALUE", length = 30)
    private String variableValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VARIABLE_ID", nullable = false, referencedColumnName = "VARIABLE_ID")
    private ProductVariable productVariable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, referencedColumnName = "PRODUCT_ID")
    private Product product;

}
