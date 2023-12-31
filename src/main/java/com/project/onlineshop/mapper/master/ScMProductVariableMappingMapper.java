package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMProductVariableMappingDto;
import com.project.onlineshop.model.master.ScMProductVariableMapping;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMProductVariableMappingMapper {
    ScMProductVariableMapping toEntity(ScMProductVariableMappingDto scMProductVariableMappingDto);

    ScMProductVariableMappingDto toDto(ScMProductVariableMapping scMProductVariableMapping);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMProductVariableMapping partialUpdate(ScMProductVariableMappingDto scMProductVariableMappingDto, @MappingTarget ScMProductVariableMapping scMProductVariableMapping);
}