package com.project.onlineshop.mapper.reference;

import com.project.onlineshop.dto.reference.ScRProductCategoryDto;
import com.project.onlineshop.model.reference.ScRProductCategory;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductCategoryMapper {
    ScRProductCategory toEntity(ScRProductCategoryDto scRProductCategoryDto);

    ScRProductCategoryDto toDto(ScRProductCategory scRProductCategory);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductCategory partialUpdate(ScRProductCategoryDto scRProductCategoryDto, @MappingTarget ScRProductCategory scRProductCategory);
}