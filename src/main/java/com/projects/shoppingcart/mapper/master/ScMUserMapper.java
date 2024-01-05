package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMUserDto;
import com.projects.shoppingcart.model.master.ScMUser;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMUserMapper {

    @Mappings({
            @Mapping(source = "roleId", target = "scRRole.roleId"),
    })
    ScMUser toEntity(ScMUserDto scMUserDto);

    @Mappings({
            @Mapping(source = "scRRole.roleId", target = "roleId"),
    })
    ScMUserDto toDto(ScMUser scMUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMUser partialUpdate(ScMUserDto scMUserDto, @MappingTarget ScMUser scMUser);

    @Mappings({
            @Mapping(source = "scRRole.roleId", target = "roleId"),
    })
    List<ScMUserDto> entityListToDtoList(List<ScMUser> users);
}