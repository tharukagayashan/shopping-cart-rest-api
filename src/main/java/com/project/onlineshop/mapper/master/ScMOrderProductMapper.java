package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMOrderProductDto;
import com.project.onlineshop.model.master.ScMOrderProduct;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMOrderProductMapper {
    ScMOrderProduct toEntity(ScMOrderProductDto scMOrderProductDto);

    ScMOrderProductDto toDto(ScMOrderProduct scMOrderProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMOrderProduct partialUpdate(ScMOrderProductDto scMOrderProductDto, @MappingTarget ScMOrderProduct scMOrderProduct);
}