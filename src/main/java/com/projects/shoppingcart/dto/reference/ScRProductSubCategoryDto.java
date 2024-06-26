package com.projects.shoppingcart.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScRProductSubCategoryDto {
    private Long subCategoryId;
    private String name;
    private String description;
    private String code;

    private Long categoryId;

    private ScRProductCategoryDto scRProductCategory;
}
