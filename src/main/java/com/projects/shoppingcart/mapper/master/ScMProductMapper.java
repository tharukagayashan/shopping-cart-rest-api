package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMProductDto;
import com.projects.shoppingcart.model.master.ScMProduct;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMProductMapper {
    ScMProduct toEntity(ScMProductDto scMProductDto);

    ScMProductDto toDto(ScMProduct scMProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMProduct partialUpdate(ScMProductDto scMProductDto, @MappingTarget ScMProduct scMProduct);
}