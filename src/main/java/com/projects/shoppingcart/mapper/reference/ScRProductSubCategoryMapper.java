package com.projects.shoppingcart.mapper.reference;

import com.projects.shoppingcart.dto.reference.ScRProductSubCategoryDto;
import com.projects.shoppingcart.model.reference.ScRProductSubCategory;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductSubCategoryMapper {
    ScRProductSubCategory toEntity(ScRProductSubCategoryDto scRProductSubCategoryDto);

    ScRProductSubCategoryDto toDto(ScRProductSubCategory scRProductSubCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductSubCategory partialUpdate(ScRProductSubCategoryDto scRProductSubCategoryDto, @MappingTarget ScRProductSubCategory scRProductSubCategory);

    List<ScRProductSubCategoryDto> entityListToDtoList(List<ScRProductSubCategory> all);
}