package com.project.onlineshop.dto.reference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ScRProductTypeDto {
    private Long typeId;
    private String name;
    private String description;
    private String code;
}
