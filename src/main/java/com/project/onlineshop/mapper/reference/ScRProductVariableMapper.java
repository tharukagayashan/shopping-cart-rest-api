package com.project.onlineshop.mapper.reference;

import com.project.onlineshop.dto.reference.ScRProductVariableDto;
import com.project.onlineshop.model.reference.ScRProductVariable;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductVariableMapper {
    ScRProductVariable toEntity(ScRProductVariableDto scRProductVariableDto);

    ScRProductVariableDto toDto(ScRProductVariable scRProductVariable);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductVariable partialUpdate(ScRProductVariableDto scRProductVariableDto, @MappingTarget ScRProductVariable scRProductVariable);
}