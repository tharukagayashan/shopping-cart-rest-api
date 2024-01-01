package com.projects.shoppingcart.model.master;

import com.projects.shoppingcart.model.reference.ScRProductVariable;
import lombok.*;

import javax.persistence.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "SC_M_PRODUCT_VARIABLE_MAPPING", indexes = {
        @Index(name = "SC_M_PRODUCT_VARIABLE_MAPPING_SC_R_VARIABLE1_idx", columnList = "VARIABLE_ID"),
        @Index(name = "SC_M_PRODUCT_VARIABLE_MAPPING_SC_M_PRODUCT1_idx", columnList = "PRODUCT_ID")
})
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
