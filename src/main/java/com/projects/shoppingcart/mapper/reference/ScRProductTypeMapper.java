package com.projects.shoppingcart.mapper.reference;

import com.projects.shoppingcart.dto.reference.ScRProductTypeDto;
import com.projects.shoppingcart.model.reference.ScRProductType;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductTypeMapper {
    ScRProductType toEntity(ScRProductTypeDto scRProductTypeDto);

    ScRProductTypeDto toDto(ScRProductType scRProductType);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductType partialUpdate(ScRProductTypeDto scRProductTypeDto, @MappingTarget ScRProductType scRProductType);

    List<ScRProductTypeDto> entityListToDtoList(List<ScRProductType> productTypes);
}