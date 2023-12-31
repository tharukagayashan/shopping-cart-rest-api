package com.project.onlineshop.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScRProductBrandDto {
    private Long brandId;
    private String name;
    private String description;
}
