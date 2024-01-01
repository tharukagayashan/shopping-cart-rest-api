package com.projects.shoppingcart.mapper.reference;

import com.projects.shoppingcart.dto.reference.ScRProductCategoryDto;
import com.projects.shoppingcart.model.reference.ScRProductCategory;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductCategoryMapper {
    ScRProductCategory toEntity(ScRProductCategoryDto scRProductCategoryDto);

    ScRProductCategoryDto toDto(ScRProductCategory scRProductCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductCategory partialUpdate(ScRProductCategoryDto scRProductCategoryDto, @MappingTarget ScRProductCategory scRProductCategory);

    List<ScRProductCategoryDto> entityListToDtoList(List<ScRProductCategory> productCategories);
}