package com.projects.shoppingcart.dto.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMProductVariableMappingDto implements Serializable {
    private Long variableMappingId;
    private String variableValue;

    private Long productId;
    private Long variableId;
//    private ScRProductVariableDto scRProductVariable;
//    private ScMProductDto scMProduct;
}