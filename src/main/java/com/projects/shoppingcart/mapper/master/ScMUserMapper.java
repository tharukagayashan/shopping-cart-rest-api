package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMUserDto;
import com.projects.shoppingcart.model.master.ScMUser;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMUserMapper {
    ScMUser toEntity(ScMUserDto scMUserDto);

    ScMUserDto toDto(ScMUser scMUser);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMUser partialUpdate(ScMUserDto scMUserDto, @MappingTarget ScMUser scMUser);

    List<ScMUserDto> entityListToDtoList(List<ScMUser> users);
}