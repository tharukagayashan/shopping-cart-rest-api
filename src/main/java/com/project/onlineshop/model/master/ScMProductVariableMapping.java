package com.project.onlineshop.model.master;

import com.project.onlineshop.model.reference.ScRProductVariable;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_M_PRODUCT_VARIABLE_MAPPING")
public class ScMProductVariableMapping {

    @Id
    @GeneratedValue(generator = "SC_M_PRODUCT_VARIABLE_MAPPING", strategy = GenerationType.IDENTITY)
    @Column(name = "VARIABLE_MAPPING_ID")
    private Long variableMappingId;

    @Column(name = "VARIABLE_VALUE", length = 30)
    private String variableValue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VARIABLE_ID", nullable = false, referencedColumnName = "VARIABLE_ID")
    private ScRProductVariable scRProductVariable;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID", nullable = false, referencedColumnName = "PRODUCT_ID")
    private ScMProduct scMProduct;

}
