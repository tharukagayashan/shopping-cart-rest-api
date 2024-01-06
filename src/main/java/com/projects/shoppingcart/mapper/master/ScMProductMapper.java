package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.dto.master.ScMProductVariableMappingDto;
import com.projects.shoppingcart.dto.other.SingleProductDto;
import com.projects.shoppingcart.model.master.ScMProduct;
import com.projects.shoppingcart.model.master.ScMProductVariableMapping;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMProductMapper {

    @Mappings({
            @Mapping(target = "scRProductBrand.brandId", source = "brandId"),
            @Mapping(target = "scRProductSubCategory.subCategoryId", source = "subCategoryId")
    })
    ScMProduct toEntity(ScMProductDto scMProductDto);

    @Mappings({
            @Mapping(target = "brandId", source = "scRProductBrand.brandId"),
            @Mapping(target = "subCategoryId", source = "scRProductSubCategory.subCategoryId"),
    })
    ScMProductDto toDto(ScMProduct scMProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMProduct partialUpdate(ScMProductDto scMProductDto, @MappingTarget ScMProduct scMProduct);

    @Mappings({
            @Mapping(target = "brandId", source = "scRProductBrand.brandId"),
            @Mapping(target = "subCategoryId", source = "scRProductSubCategory.subCategoryId")
    })
    List<ScMProductVariableMappingDto> entityListToDtoList(List<ScMProductVariableMapping> productVariableMappingList);

    @Mappings({
            @Mapping(target = "brandId", source = "scRProductBrand.brandId"),
            @Mapping(target = "subCategoryId", source = "scRProductSubCategory.subCategoryId"),
            @Mapping(target = "brandName", source = "scRProductBrand.name"),
            @Mapping(target = "subCategoryName", source = "scRProductSubCategory.name")
    })
    List<SingleProductDto> toSingleProductDtoList(List<ScMProduct> scMProductList);

    @Mappings({
            @Mapping(target = "brandId", source = "scRProductBrand.brandId"),
            @Mapping(target = "subCategoryId", source = "scRProductSubCategory.subCategoryId"),
            @Mapping(target = "brandName", source = "scRProductBrand.name"),
            @Mapping(target = "subCategoryName", source = "scRProductSubCategory.name")
    })
    SingleProductDto toSingleProductDto(ScMProduct scMProduct);
}