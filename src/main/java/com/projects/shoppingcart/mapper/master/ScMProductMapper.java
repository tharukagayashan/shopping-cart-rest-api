package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.model.master.ScMProduct;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMProductMapper {

    @Mappings({
            @Mapping(target = "scRProductBrand.brandId", source = "brandId"),
            @Mapping(target = "scRProductSubCategory.subCategoryId", source = "subCategoryId")
    })
    ScMProduct toEntity(ScMProductDto scMProductDto);

    @Mappings({
            @Mapping(target = "brandId", source = "scRProductBrand.brandId"),
            @Mapping(target = "subCategoryId", source = "scRProductSubCategory.subCategoryId")
    })
    ScMProductDto toDto(ScMProduct scMProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMProduct partialUpdate(ScMProductDto scMProductDto, @MappingTarget ScMProduct scMProduct);
}