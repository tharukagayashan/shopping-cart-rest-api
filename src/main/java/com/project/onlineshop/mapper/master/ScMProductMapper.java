package com.project.onlineshop.mapper.master;

import com.project.onlineshop.dto.master.ScMProductDto;
import com.project.onlineshop.model.master.ScMProduct;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMProductMapper {
    ScMProduct toEntity(ScMProductDto scMProductDto);

    ScMProductDto toDto(ScMProduct scMProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMProduct partialUpdate(ScMProductDto scMProductDto, @MappingTarget ScMProduct scMProduct);
}