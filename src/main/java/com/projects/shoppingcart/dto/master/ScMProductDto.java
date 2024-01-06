package com.projects.shoppingcart.dto.master;

import com.projects.shoppingcart.dto.reference.ScRProductBrandDto;
import com.projects.shoppingcart.dto.reference.ScRProductSubCategoryDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScMProductDto {
    private Long productId;
    private String name;
    private String description;
    private String code;
    private Float price;
    private Integer quantity;
    private String image;

    private Long subCategoryId;
    private Long brandId;

    private ScRProductSubCategoryDto scRProductSubCategory;
    private ScRProductBrandDto scRProductBrand;
}
