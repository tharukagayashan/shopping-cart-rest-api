package com.project.onlineshop.mapper.reference;

import com.project.onlineshop.dto.reference.ScRProductSubCategoryDto;
import com.project.onlineshop.model.reference.ScRProductSubCategory;
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