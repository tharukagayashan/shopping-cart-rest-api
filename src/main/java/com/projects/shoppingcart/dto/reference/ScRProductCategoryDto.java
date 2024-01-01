package com.projects.shoppingcart.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScRProductCategoryDto {
    private Long categoryId;
    private String name;
    private String description;
    private String code;
    private ScRProductTypeDto scRProductType;
}
