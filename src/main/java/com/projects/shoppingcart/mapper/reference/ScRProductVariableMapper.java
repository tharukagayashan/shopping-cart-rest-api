package com.projects.shoppingcart.mapper.reference;

import com.projects.shoppingcart.dto.reference.ScRProductVariableDto;
import com.projects.shoppingcart.model.reference.ScRProductVariable;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRProductVariableMapper {
    ScRProductVariable toEntity(ScRProductVariableDto scRProductVariableDto);

    ScRProductVariableDto toDto(ScRProductVariable scRProductVariable);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRProductVariable partialUpdate(ScRProductVariableDto scRProductVariableDto, @MappingTarget ScRProductVariable scRProductVariable);

    List<ScRProductVariableDto> entityListToDtoList(List<ScRProductVariable> all);
}