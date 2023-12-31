package com.project.onlineshop.mapper.reference;

import com.project.onlineshop.dto.reference.ScRProductBrandDto;
import com.project.onlineshop.model.reference.ScRProductBrand;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductBrandMapper {
    ScRProductBrand toEntity(ScRProductBrandDto scRProductBrandDto);

    ScRProductBrandDto toDto(ScRProductBrand scRProductBrand);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductBrand partialUpdate(ScRProductBrandDto scRProductBrandDto, @MappingTarget ScRProductBrand scRProductBrand);
}