package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMStaffDto;
import com.project.onlineshop.model.master.ScMStaff;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMStaffMapper {
    ScMStaff toEntity(ScMStaffDto scMStaffDto);

    ScMStaffDto toDto(ScMStaff scMStaff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMStaff partialUpdate(ScMStaffDto scMStaffDto, @MappingTarget ScMStaff scMStaff);

    List<ScMStaffDto> entityListToDtoList(List<ScMStaff> staffs);
}