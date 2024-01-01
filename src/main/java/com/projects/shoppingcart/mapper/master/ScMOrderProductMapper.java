package com.projects.shoppingcart.mapper.master;

import com.projects.shoppingcart.dto.master.ScMOrderProductDto;
import com.projects.shoppingcart.model.master.ScMOrderProduct;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ScMOrderProductMapper {
    ScMOrderProduct toEntity(ScMOrderProductDto scMOrderProductDto);

    ScMOrderProductDto toDto(ScMOrderProduct scMOrderProduct);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ScMOrderProduct partialUpdate(ScMOrderProductDto scMOrderProductDto, @MappingTarget ScMOrderProduct scMOrderProduct);
}