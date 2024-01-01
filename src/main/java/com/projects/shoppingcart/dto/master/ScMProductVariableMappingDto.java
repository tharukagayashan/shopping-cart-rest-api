package com.projects.shoppingcart.dto.master;

import com.projects.shoppingcart.dto.reference.ScRProductVariableDto;
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
    private ScRProductVariableDto scRProductVariable;
    private ScMProductDto scMProduct;
}