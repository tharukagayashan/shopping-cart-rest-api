package com.project.onlineshop.mapper.reference;

import com.project.onlineshop.dto.reference.ScRRoleDto;
import com.project.onlineshop.model.reference.ScRRole;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScRRoleMapper {
    ScRRole toEntity(ScRRoleDto scRRoleDto);

    ScRRoleDto toDto(ScRRole scRRole);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScRRole partialUpdate(ScRRoleDto scRRoleDto, @MappingTarget ScRRole scRRole);
}