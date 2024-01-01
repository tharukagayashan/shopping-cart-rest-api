package com.projects.shoppingcart.mapper.reference;

import com.projects.shoppingcart.dto.reference.ScRProductBrandDto;
import com.projects.shoppingcart.model.reference.ScRProductBrand;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductBrandMapper {
    ScRProductBrand toEntity(ScRProductBrandDto scRProductBrandDto);

    ScRProductBrandDto toDto(ScRProductBrand scRProductBrand);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductBrand partialUpdate(ScRProductBrandDto scRProductBrandDto, @MappingTarget ScRProductBrand scRProductBrand);

    List<ScRProductBrandDto> entityListToDtoList(List<ScRProductBrand> brands);
}