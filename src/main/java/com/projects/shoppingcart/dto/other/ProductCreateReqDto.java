package com.projects.shoppingcart.dto.other;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductCreateReqDto {
    @JsonProperty("name")
    @NotNull
    @NotEmpty
    private String name;
    @JsonProperty("description")
    @NotNull
    @NotEmpty
    private String description;
    @JsonProperty("code")
    @NotNull
    @NotEmpty
    private String code;
    @JsonProperty("price")
    @NotNull
    @Min(0)
    private Float price;
    @JsonProperty("quantity")
    @NotNull
    @Min(0)
    private Integer quantity;
    @JsonProperty("image")
    @NotNull
    @NotEmpty
    private String image;
    @JsonProperty("subCategoryId")
    @NotNull
    private Long subCategoryId;
    @JsonProperty("brandId")
    @NotNull
    private Long brandId;
    @JsonProperty("discount")
    @NotNull
    @Min(0)
    private Float discount;

    private List<ProductVariableDto> productVariables;
}