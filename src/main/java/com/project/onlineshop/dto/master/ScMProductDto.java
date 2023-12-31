package com.project.onlineshop.dto.master;

import com.project.onlineshop.dto.reference.ScRProductBrandDto;
import com.project.onlineshop.dto.reference.ScRProductSubCategoryDto;
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
    private ScRProductSubCategoryDto scRProductSubCategory;
    private ScRProductBrandDto scRProductBrand;
}
