package com.project.onlineshop.model.reference;

import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_R_PRODUCT_VARIABLE")
public class ScRProductVariable {

    @Id
    @GeneratedValue(generator = "SC_R_PRODUCT_VARIABLE", strategy = GenerationType.IDENTITY)
    @Column(name = "VARIABLE_ID")
    private Long variableId;

    @Column(name = "VARIABLE_NAME", length = 30)
    private String name;

}
