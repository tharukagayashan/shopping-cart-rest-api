package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMProductVariableMappingDto;
import com.projects.shoppingcart.model.master.ScMProductVariableMapping;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMProductVariableMappingMapper {
    ScMProductVariableMapping toEntity(ScMProductVariableMappingDto scMProductVariableMappingDto);

    ScMProductVariableMappingDto toDto(ScMProductVariableMapping scMProductVariableMapping);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMProductVariableMapping partialUpdate(ScMProductVariableMappingDto scMProductVariableMappingDto, @MappingTarget ScMProductVariableMapping scMProductVariableMapping);
}