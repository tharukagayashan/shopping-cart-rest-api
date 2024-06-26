package com.projects.shoppingcart.dto.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductVariableCreateReqDto {
    @NotNull
    @NotEmpty
    @NotBlank
    private String name;
}
