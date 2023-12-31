package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMOrderDto;
import com.project.onlineshop.model.master.ScMOrder;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMOrderMapper {
    ScMOrder toEntity(ScMOrderDto scMOrderDto);

    ScMOrderDto toDto(ScMOrder scMOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMOrder partialUpdate(ScMOrderDto scMOrderDto, @MappingTarget ScMOrder scMOrder);
}