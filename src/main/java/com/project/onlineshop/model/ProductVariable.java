package com.project.onlineshop.model;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PRODUCT_VARIABLE")
public class ProductVariable {

    @Id
    @GeneratedValue(generator = "PRODUCT_VARIABLE", strategy = GenerationType.IDENTITY)
    @Column(name = "VARIABLE_ID")
    private Long variableId;

    @Column(name = "VARIABLE_NAME", length = 30)
    private String name;

}
