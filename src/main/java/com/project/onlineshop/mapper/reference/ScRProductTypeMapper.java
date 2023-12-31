package com.project.onlineshop.mapper.reference;

import com.project.onlineshop.dto.reference.ScRProductTypeDto;
import com.project.onlineshop.model.reference.ScRProductType;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductTypeMapper {
    ScRProductType toEntity(ScRProductTypeDto scRProductTypeDto);

    ScRProductTypeDto toDto(ScRProductType scRProductType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductType partialUpdate(ScRProductTypeDto scRProductTypeDto, @MappingTarget ScRProductType scRProductType);
}